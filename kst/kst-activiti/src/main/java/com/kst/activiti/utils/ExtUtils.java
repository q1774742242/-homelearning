package com.kst.activiti.utils;

import com.kst.activiti.dto.ProcessDefDTO;
import com.kst.activiti.dto.TaskDTO;
import com.kst.common.utils.ApplicationContextRegister;
import com.kst.common.utils.StringUtils;
import com.kst.sys.api.entity.Role;
import com.kst.sys.api.entity.User;
import com.kst.common.enums.HttpCodeEnum;
import com.kst.common.exception.MyException;
import com.kst.sys.api.service.IUserService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.TaskInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xufei.
 * @createTime: 2017/8/11.
 */
public class ExtUtils {
//    static HistoryService historyService = Application.getBean(HistoryService.class);
    private static IUserService userService = ApplicationContextRegister.getBean(IUserService.class);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public static User getUserInfo(String userId) {
        return userService.getById(userId);
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    public static Set<Role> getUserRoles(String userId) {
        return userService.findUserByLoginName(userId).getRoleLists();
    }

    /**
     * 节点对应的中文名称
     * @param type
     * @return
     */
    public static String parseToZhType(String type) {
        Map<String, String> types = new HashMap<>(16);
        types.put("userTask", "用户任务");
        types.put("serviceTask", "系统任务");
        types.put("startEvent", "开始节点");
        types.put("endEvent", "结束节点");
        types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
        types.put("inclusiveGateway", "并行处理任务");
        types.put("callActivity", "子流程");
        return types.get(type) == null ? type : types.get(type);
    }

    /**
     * 将SysRole转为activiti的entity
     * @param role
     * @return
     */
//    public static GroupEntity toActivitiGroup(Role role){
////        GroupEntity groupEntity = new GroupEntity();
////        groupEntity.setId(role.getId() + "");
////        groupEntity.setName(role.getName());
////        //groupEntity.setType(role.getDataScope());
////        groupEntity.setRevision(1);
////        return groupEntity;
//    }

    /**
     * 将SysUser转为activiti的entity
     * @param user
     * @return
     */
//    public static UserEntity toActivitiUser(User user){
////        UserEntity userEntity = new UserEntity();
////        userEntity.setId(user.getId() + "");
////        userEntity.setFirstName(user.getNickName());
////        userEntity.setLastName(StringUtils.EMPTY);
////        userEntity.setPassword(user.getPassword());
////        userEntity.setEmail(user.getEmail());
////        userEntity.setRevision(1);
////        return userEntity;
//    }

    /**
     * 抽取流程定义需要返回的内容
     * @param processDefinition
     * @param deployment
     * @return
     */
    public static ProcessDefDTO toProcessDefDTO(ProcessDefinition processDefinition, Deployment deployment) {
        if (null == processDefinition || null == deployment) {
            throw new MyException(HttpCodeEnum.INVALID_REQUEST);
        }
        ProcessDefDTO dto = new ProcessDefDTO();
        dto.category = processDefinition.getCategory();
        dto.processonDefinitionId = processDefinition.getId();
        dto.key = processDefinition.getKey();
        dto.name = deployment.getName();
        dto.revision = processDefinition.getVersion();
        dto.deploymentTime = deployment.getDeploymentTime().getTime();
        dto.xmlName = processDefinition.getResourceName();
        dto.picName = processDefinition.getDiagramResourceName();
        dto.deploymentId = deployment.getId();
        dto.suspend = processDefinition.isSuspended();
        dto.description = processDefinition.getDescription();
        return dto;
    }

//    public static TaskDTO toTaskDTO(TaskInfo task, String status, ProcessDefinition processDefinition, Deployment deployment) {
////        TaskDTO dto = new TaskDTO();
////        dto.setTaskId(task.getId());
////        dto.setTaskName(task.getName());
//////        dto.setTime(historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartTime().getTime());
////        dto.setVariable(task.getTaskLocalVariables());
////        dto.setPdName(deployment.getName());
////        dto.setVersion("V:" + processDefinition.getVersion());
////        dto.setProcessInstanceId(task.getProcessInstanceId());
////        dto.setStatus(status);
////        dto.setTitle((String) task.getProcessVariables().get("title"));
////        dto.setNodeKey(task.getTaskDefinitionKey());
////        dto.setProcessDefKey(processDefinition.getKey());
////        dto.setCategory(task.getCategory());
////        return dto;
//    }

//    public static TaskListDTO toTaskListDTO(PageResultsDTO dto, String category) {
//        TaskListDTO taskList = new TaskListDTO();
//        taskList.setCategory(category);
//        taskList.setPage(dto.page);
//        taskList.setPageSize(dto.pageSize);
//        taskList.setTotalCount((int) dto.totalCount);
//        taskList.setTasks(dto.getList());
//        return taskList;
//    }
//
//    public static TaskListDTO toTaskListDTO(List<TaskDTO> tasks, String category, int page, int pageSize, long totalCount) {
//        TaskListDTO taskList = new TaskListDTO();
//        taskList.setCategory(category);
//        taskList.setPage(page);
//        taskList.setPageSize(pageSize);
//        taskList.setTotalCount((int) totalCount);
//        taskList.setTasks(tasks);
//        return taskList;
//    }
}