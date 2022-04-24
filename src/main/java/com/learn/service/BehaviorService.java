package com.learn.service;

import com.learn.mapper.BehaviorMapper;
import com.learn.model.behavior.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户行为
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@Service
public class BehaviorService {

    @Autowired
    private BehaviorMapper behaviorMapper;


    /**
     * pv
     *
     * @param
     * @return
     */
    public List<PV> PVService() {

        List<PV> pvList = behaviorMapper.getPV();
        return pvList;
    }


    /**
     * uv
     *
     * @param
     * @return
     */
    public List<UV> UVService(int uid) {

        List<UV> uvList = behaviorMapper.getUV(uid);
        return uvList;
    }


    /**
     * 总体学习兴趣统计
     * uv
     *
     * @param
     * @return
     */
    public List<LearningInteres> learningInteres() {

        List<LearningInteres> learningInteresList = behaviorMapper.getLearningInteres();
        return learningInteresList;
    }


    /**
     * 总体学习时段统计
     * uv
     *
     * @param
     * @return
     */
    public List<TimeSlot> getTimeSlot() {

        List<TimeSlot> timeSlot = behaviorMapper.getTimeSlot();
        return timeSlot;
    }


    /**
     * 学习专注度
     *
     * @param
     * @return
     */
    public int ConcentrationService(String uid) {
        int concentration = behaviorMapper.getConcentration(uid);
        //log.info("专注度："+concentration);

        return concentration;
    }


    /**
     * 用户学习时长
     *
     * @param
     * @return
     */
    public LearningDuration LearningDurationService(int uid) {

        LearningDuration learningDuration = new LearningDuration();
        learningDuration.setTotalLearningD(behaviorMapper.getAllLearningDuration(uid));

        learningDuration.setLd(behaviorMapper.getLearningDuration(uid));

        return learningDuration;
    }


    /**
     * 用户时段分析
     *
     * @param
     * @return
     */
    public List<UserTimeSlot> userTimeSlotService(int uid) {

        List<UserTimeSlot> userTimeSlot = behaviorMapper.getUserTimeSlot(uid);
        return userTimeSlot;
    }


    /**
     * 内容推荐
     *
     * @return
     */
    public ArrayList<Content> centont(int uid) {


        ArrayList<Content> v = recommend(uid);

        System.out.println("正在生成针对用户id为" + uid + "的推荐...");
        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i).getW());
            System.out.println("第" + (i + 1) + "个推荐: 题目:" + v.get(i).getContent() + "");

        }
        return v;

    }

    //通过计算余弦相似度并取TopN, 返回为uid的用户生成的3个推荐文章
    public ArrayList<Content> recommend(int uid) {

        ArrayList<Likes> likeLists;                                       //其他用户喜欢的内容列表

        ArrayList<User> users = behaviorMapper.getAllUsers();                   //所有用户列表
        ArrayList<Content> content = behaviorMapper.getAllContent();               //所有内容列表

        int[][] curMatrix = new int[content.size() + 3][content.size() + 3];   //当前矩阵
        int[][] comMatrix = new int[content.size() + 3][content.size() + 3];   //共现矩阵
        int[] N = new int[content.size() + 3];                              //喜欢内容的人数

        for (User user : users) {
            if (user.getUser_id() == uid) continue;                    //当前用户则跳过

            // System.out.println(user.getUser_id()+"=======");
            likeLists = behaviorMapper.findLikesByUser(user.getUser_id()); //当前用户的喜欢列表
            // System.out.println(likeLists);

            for (int i = 0; i < content.size(); i++)
                for (int j = 0; j < content.size(); j++)
                    curMatrix[i][j] = 0;                               //清空矩阵

            for (int i = 0; i < likeLists.size(); i++) {
                int pid1 = likeLists.get(i).getContent_id();
                ++N[pid1];
                for (int j = i + 1; j < likeLists.size(); j++) {
                    int pid2 = likeLists.get(j).getContent_id();
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }

            //累加所有矩阵, 得到共现矩阵
            for (int i = 0; i < content.size(); i++) {
                for (int j = 0; j < content.size(); j++) {
                    int pid1 = content.get(i).getContent_id(), pid2 = content.get(j).getContent_id();
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }


        TreeSet<Content> preList = new TreeSet<Content>(new Comparator<Content>() {
            @Override
            public int compare(Content o1, Content o2) {
                if (o1.getW() != o2.getW())
                    return (int) (o1.getW() - o2.getW()) * 100;
                else
                    return o1.getLikeCnt() - o2.getLikeCnt();
            }
        }); //预处理的列表

        likeLists = behaviorMapper.findLikesByUser(uid);       //当前用户喜欢的论文列表
        boolean[] used = new boolean[content.size() + 3];  //判重数组
        for (Likes like : likeLists) {
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            Content tmp;                           //当前的论文

            int i = like.getContent_id();
            for (Content paper : content) {
                if (like.getContent_id() == paper.getContent_id()) continue;
                int j = paper.getContent_id();

                Nij = comMatrix[i][j];
                Wij = (double) Nij / Math.sqrt(N[i] * N[j]); //计算余弦相似度

                tmp = behaviorMapper.findPaperById(paper.getContent_id());

                tmp.setW(Wij);

                if (used[tmp.getContent_id()]) continue;
                preList.add(tmp);
                used[tmp.getContent_id()] = true;
            }
        }

        ArrayList<Content> recomLists = new ArrayList<>();      //生成的推荐结果
        for (int i = 0; preList.size() > 0 && i < 3; i++) {
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }

        return recomLists;
    }


    /**
     * 标签
     *
     * @return
     */
    public Lable1 userlabel(int uid) {

        Lable lable = behaviorMapper.userLabelSQL(uid);
        Lable1 lable1 = new Lable1();
        lable1.setUserlabel("喜爱" + lable.getType() + "类课程");

        return lable1;

    }

}
