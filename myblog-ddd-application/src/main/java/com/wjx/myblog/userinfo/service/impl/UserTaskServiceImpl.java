package com.wjx.myblog.userinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.common.utils.LongUtil;
import com.wjx.common.utils.SnowflakeIdGenerator;
import com.wjx.myblog.domain.common.MyblogErrorCodeEnum;
import com.wjx.myblog.domain.userinfo.UserInfo;
import com.wjx.myblog.domain.userinfo.UserInfoDomainService;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.infrastructure.config.jwt.JwtTokenUtil;
import com.wjx.myblog.infrastructure.database.convertor.UserTaskConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import com.wjx.myblog.infrastructure.database.mapper.UserTaskMapper;
import com.wjx.myblog.infrastructure.gatewayimpl.UserInfoGatewayImpl;
import com.wjx.myblog.userinfo.api.UserTaskService;
import com.wjx.myblog.userinfo.dto.UserTaskCreateCmd;
import com.wjx.myblog.userinfo.dto.UserTaskDTO;
import com.wjx.myblog.userinfo.dto.UserTaskDelCmd;
import com.wjx.myblog.userinfo.dto.UserTaskUpdateCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userTask")
public class UserTaskServiceImpl extends BaseService implements UserTaskService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private UserTaskConvertor userTaskConvertor;
    @Autowired
    private UserInfoGatewayImpl userInfoGateway;
    @Autowired
    private UserInfoDomainService userInfoDomainService;

    @PostMapping("/getTaskById")
    public ResponseEntity<ApiResult<UserTaskDTO>> getTaskById(@RequestBody UserTaskDelCmd delCmd) {
        UserTaskDO userTaskDO = userTaskMapper.selectById(delCmd.getTaskId());
        return ResponseEntity.ok(ok(userTaskConvertor.convertUserTaskDTO(userTaskDO)));
    }

    @PostMapping("/getTasks")
    public ResponseEntity<ApiResult<ArrayList<UserTaskDTO>>> getTasksByUsername(HttpServletRequest request, HttpServletResponse response) {
        String requestTokenHeader = request.getHeader("Authorization");
        List<UserTaskDTO> res = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            Long userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
            if (userId != null) {
                res = userInfoGateway.getTasksByUserId(userId);
            }
        }
        return ResponseEntity.ok(ok(res));
    }

    @PostMapping("/insertTask")
    public ResponseEntity<ApiResult<UserTaskDTO>> insertTask(HttpServletRequest request, @RequestBody UserTaskCreateCmd createCmd) {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null) {
            return ResponseEntity.ok(fail(MyblogErrorCodeEnum.EMPTY_TOKEN.code(), "请求token为空！"));
        }
        String jwtToken = requestTokenHeader.substring(7);
        Long userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
        if (userId == null) {
            return ResponseEntity.ok(fail(401, "Token无效或已过期"));
        }
        UserInfo userInfo = userInfoGateway.getById(userId);
        UserTask userTask = userInfo.raiseTask(createCmd.getDeadline(), createCmd.getDescription(), createCmd.getAlarm());
        userTaskMapper.insert(userTaskConvertor.toDataObject(userTask));
        return ResponseEntity.ok(ok(userTaskConvertor.toDataTransferObj(userTask)));
    }

    @PostMapping("/deleteTask")
    public ResponseEntity<ApiResult> deleteTask(HttpServletRequest request, @RequestBody UserTaskDelCmd delCmd) {
        userTaskMapper.deleteById(LongUtil.convertStr2Long(delCmd.getTaskId()));
        return ResponseEntity.ok(ok());
    }

    @PostMapping("/modifyTask")
    public ResponseEntity<ApiResult<UserTaskDTO>> modifyTask(HttpServletRequest request, @RequestBody UserTaskUpdateCmd updateCmd) {
        UserTask userTask = userInfoGateway.getTaskById(updateCmd.getTaskId());
        userInfoDomainService.updateTask(userTask, updateCmd.getDeadline(), updateCmd.getDescription(), updateCmd.getAlarm(), updateCmd.getStatus());
        userTaskMapper.updateById(userTaskConvertor.toDataObject(userTask));
        return ResponseEntity.ok(ok(userTaskConvertor.toDataTransferObj(userTask)));
    }
}
