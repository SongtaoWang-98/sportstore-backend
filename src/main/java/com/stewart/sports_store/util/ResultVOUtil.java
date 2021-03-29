package com.stewart.sports_store.util;

import com.stewart.sports_store.enums.ResultCode;
import com.stewart.sports_store.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO fail(ResultCode resultCode){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("fail");
        resultVO.setData(resultCode);
        return resultVO;
    }
}
