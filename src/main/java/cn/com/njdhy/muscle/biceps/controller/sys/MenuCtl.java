
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.UserErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.model.ZTree;
import cn.com.njdhy.muscle.biceps.service.sys.SysMenuService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <类功能简述> 菜单控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuCtl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuCtl.class);

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 分页查询菜单列表
     *
     * @param params     查询参数
     * @param pageNumber 当前页码
     * @param pageSize   每页显示大小
     * @return 列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNumber, Integer pageSize) {
        Query queryParam = new Query(params);
        PageInfo<SysMenu> result = sysMenuService.queryList(queryParam, pageNumber, pageSize);

        return Result.success(result.getTotal(), result.getList());
    }


    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {

        SysMenu model = sysMenuService.queryById(id);
        if (ObjectUtils.isEmpty(model)) {
            model = new SysMenu();
        }
        return Result.success().put("model", model);
    }


    /**
     * 保存
     *
     * @param model 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysMenu model) {

        try {
            // 校验参数
            // TODO: 2018/3/14
            // 执行入库操作
            sysMenuService.insert(model);
        } catch (ApplicationException e) {
            return Result.error(UserErrorCode.SYS_USER_SAVE_APP_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(UserErrorCode.SYS_USER_SAVE_ERROR_CODE, UserErrorCode.SYS_USER_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param model 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysMenu model) {

        try {
            // 校验参数
            // TODO: 2018/3/14

            // 执行修改
            sysMenuService.update(model);
        } catch (RuntimeException e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_APP_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(UserErrorCode.SYS_USER_UPDATE_ERROR_CODE, UserErrorCode.SYS_USER_UPDATE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 删除多个记录
     *
     * @param ids 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/delete")
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {
            // 校验参数 todo
            sysMenuService.deleteByIds(ids);
        } catch (ApplicationException e) {
            return Result.error(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    /**
     * 查询除按鈕以外的菜單(增加菜单时选择父级菜单用)
     */
    @RequestMapping("/queryMenu")
    public Result queryMenu() {
        List<ZTree> treeList = new ArrayList<>();
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryMenu();
        if (menuList != null && menuList.size() != 0) {
            for (SysMenu menu : menuList) {
                ZTree tree = new ZTree();
                tree.setMenuId(String.valueOf(menu.getId()));
                tree.setParentId(menu.getParentId());
                tree.setName(menu.getName());
                treeList.add(tree);
            }
            ZTree zTree = new ZTree();
            zTree.setMenuId("0");
            zTree.setParentId("-1");
            zTree.setName("一级菜单");

            treeList.add(zTree);
        }
        return Result.success().put("model", treeList);
    }

    /**
     * 查询所有菜单(新增角色时用)
     *
     * @return
     */
    @RequestMapping("/queryAllMenuInsert")
    public Result queryAllMenu() {

        List<ZTree> treeList = new ArrayList<>();
        //查询列表数据
        List<SysMenu> allMenuList = sysMenuService.queryAllMenu();

        if (allMenuList != null && allMenuList.size() != 0) {
            for (SysMenu menu : allMenuList) {
                ZTree tree = new ZTree();
                tree.setMenuId(String.valueOf(menu.getId()));
                tree.setParentId(menu.getParentId());
                tree.setName(menu.getName());
                treeList.add(tree);
            }
        }

        return Result.success().put("model", treeList);
    }

    @RequestMapping("queryAllMenuUpdate")
    public Result queryAllMenuUpdate(SysRole role) {
        List<ZTree> zTreeList = new ArrayList<>();
        try {
            //查询列表数据
            List<SysMenu> allMenuList = sysMenuService.queryAllMenu();

            //根据角色ID查询该角色拥有的权限
            List<String> roleList = sysMenuService.queryMenuByRole(String.valueOf(role.getId()));

            if (roleList != null && roleList.size() != 0) {
                if (allMenuList != null && allMenuList.size() != 0) {
                    for (SysMenu sysMenu : allMenuList) {
                        //该用户有权限设置选中属性
                        if (roleList.contains(sysMenu.getId() + "")) {

                            ZTree zTree = new ZTree();

                            zTree.setMenuId(String.valueOf(sysMenu.getId()));
                            zTree.setParentId(sysMenu.getParentId());
                            zTree.setName(sysMenu.getName());
                            zTree.setChecked(true);
                            zTreeList.add(zTree);
                        } else {
                            ZTree zTree = new ZTree();

                            zTree.setMenuId(String.valueOf(sysMenu.getId()));
                            zTree.setParentId(sysMenu.getParentId());
                            zTree.setName(sysMenu.getName());
                            zTree.setChecked(false);
                            zTreeList.add(zTree);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return Result.success().put("model", zTreeList);
    }

}
