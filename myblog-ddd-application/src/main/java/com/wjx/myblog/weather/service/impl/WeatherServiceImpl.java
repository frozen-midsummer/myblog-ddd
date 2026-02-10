package com.wjx.myblog.weather.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.myblog.infrastructure.database.convertor.ChinaCityCodeConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.ChinaCityCodeDO;
import com.wjx.myblog.infrastructure.database.mapper.ChinaCityCodeMapper;
import com.wjx.myblog.weather.api.WeatherService;
import com.wjx.myblog.weather.dto.ChinaCityCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/weather")
public class WeatherServiceImpl extends BaseService implements WeatherService {
    @Autowired
    ChinaCityCodeConvertor chinaCityCodeConvertor;
    @Autowired
    ChinaCityCodeMapper chinaCityCodeMapper;
    @Autowired
    RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    @Override
    @GetMapping("/getAllProvince")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getAllProvince() {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeLeft(ChinaCityCodeDO::getAdCode, "0000");
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }

    @Override
    @GetMapping("/getCityByProvince")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCityByProvince(@RequestParam("adCode") String adCode) {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeRight(ChinaCityCodeDO::getAdCode, adCode);
        queryWrapper.and(s -> s.notLikeLeft(ChinaCityCodeDO::getAdCode, "0000"));
        //直辖市数组
        if (!Arrays.asList("11", "12", "31", "50", "71", "81", "82").contains(adCode)) {
            queryWrapper.and(s -> s.likeLeft(ChinaCityCodeDO::getAdCode, "00"));
        }
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }

    @Override
    @GetMapping("/getCountyByCity")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCountyByCity(@RequestParam("adCode") String adCode) {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeRight(ChinaCityCodeDO::getAdCode, adCode);
        queryWrapper.and(s -> s.notLikeLeft(ChinaCityCodeDO::getAdCode, "00"));
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }

    @Override
    @GetMapping("/weatherInfo")
    public ResponseEntity<ApiResult<Serializable>> getWeatherInfo(@RequestParam("city") String city, @RequestParam("extensions") String extensions) {
        String url = String.format("https://restapi.amap.com/v3/weather/weatherInfo?city=%s&extensions=%s&key=%s", city, extensions, amapKey);
        Serializable result = restTemplate.getForObject(url, Serializable.class);
        return ResponseEntity.ok(ok(result));
    }

    @Override
    @GetMapping("/getIpLocation")
    public ResponseEntity<ApiResult<Serializable>> getIpLocation(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 处理多个 IP 的情况 (X-Forwarded-For 可能包含多个 IP)
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        System.out.println("Detected IP: " + ip);

        // 如果是本地测试，或者 IP 不合法，留空让高德自动识别请求来源 IP
        if (ip == null || "0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = "";
        }
        
        String url;
        if (ip.isEmpty()) {
            url = String.format("https://restapi.amap.com/v3/ip?key=%s", amapKey);
        } else {
            url = String.format("https://restapi.amap.com/v3/ip?ip=%s&key=%s", ip, amapKey);
        }
        
        Serializable result = restTemplate.getForObject(url, Serializable.class);
        return ResponseEntity.ok(ok(result));
    }
}
