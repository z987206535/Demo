package com.btyer.controller;

import com.btyer.dto.MoodDTO;
import com.btyer.service.MoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/mood")
public class MoodController {

    @Resource(name="MoodServiceImpl")
    private MoodService moodService;

    @GetMapping(value = "/findAll")
    public String findAll(Model model) {
        List<MoodDTO> moodList = moodService.findAll();
        model.addAttribute("moods",moodList);
        return "mood";
    }

    @GetMapping(value = "/{moodId}/praise")
    public String praise(Model model, @PathVariable(value="moodId")String moodId,
                         @RequestParam(value="userId")String userId){
        boolean isPraise = moodService.praiseMood(userId, moodId);

        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods",moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

    @RequestMapping(value = "/{moodId}/praiseForRedis")
    public String praiseForRedis(Model model, @PathVariable(value="moodId")String moodId,
                                 @RequestParam(value="userId")String userId){
        //方便使用，随机生成用户id
        Random random = new Random();
        userId = random.nextInt(100) + "";

        // 将点赞的说说id和用户id存在缓存中
        boolean isPraise = moodService.praiseMoodForRedis(userId,moodId);

        // 查询所有的说说数据
        List<MoodDTO> moodDTOList = moodService.findAllForRedis();
        model.addAttribute("moods",moodDTOList);
        model.addAttribute("isPraise",isPraise);
        return "mood";
    }


}
