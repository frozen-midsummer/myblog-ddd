package com.wjx.myblog.weather.api;

import com.wjx.common.result.result.ApiResult;
import com.wjx.myblog.weather.dto.ChinaCityCodeDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;

public interface WeatherService {
    ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getAllProvince();

    ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCityByProvince(String adCode);

    ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCountyByCity(String adCode);

    ResponseEntity<ApiResult<Serializable>> getWeatherInfo(String city, String extensions);

    ResponseEntity<ApiResult<Serializable>> getIpLocation(HttpServletRequest request);
}
