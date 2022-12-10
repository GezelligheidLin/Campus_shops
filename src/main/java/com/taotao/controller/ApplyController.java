package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.entity.Apply;
import com.taotao.service.ApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

import static com.taotao.util.SystemConstants.FALSE;

/**
 * @author YuLong
 * Date: 2022/12/10 22:00
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    /**
     * 用户申请商家权限
     * @param userId 用户账号
     * @return Result
     */
    @PutMapping("/request")
    public Result<String> requestMerchant(@RequestParam("userId") Long userId) {
        log.info("用户正在申请商家权限中。。。");
        // 先在申请表中查询该用户是否有申请记录
        Apply apply = applyService.queryApplyRecord(userId);
        if (apply != null) {
            // 存在申请记录，判断能否继续申请
            Integer enable = apply.getEnable();
            if (Objects.equals(enable, FALSE)) {
                // 暂时不能够继续申请，需等待管理员同意或拒绝
                return Result.fail("您已递交过申请，请勿重复提交申请");
            }
        }
        // 达到申请条件，执行申请方法
        applyService.saveMerchant(userId);
        return Result.success("已递交申请，待后台审核，请耐心等待");
    }
}
