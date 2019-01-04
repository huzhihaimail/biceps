
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.MenuErrorCode;
import cn.com.njdhy.muscle.biceps.exception.sys.RoleErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.model.ZTree;
import cn.com.njdhy.muscle.biceps.service.sys.SysMenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <类功能简述> 角色控制器
 *
 * @author 胡志海
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuCtl {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询角色列表
     *
     * @param params     参数列表
     * @param pageNumber 当前页码
     * @param pageSize   每页大小
     * @return 用户列表
     */
    @RequestMapping("/list")
    public Result index(@RequestParam Map<String, Object> params, Integer pageNumber, Integer pageSize) {
        PageInfo<SysMenu> result = null;
        try {
            Query queryParam = new Query(params);
            result = sysMenuService.queryList(queryParam, pageNumber, pageSize);
            List<SysMenu> sysMenus = result.getList();
            for (SysMenu sysMenu : sysMenus) {

                if (sysMenu.getParentId() == 0) {
                    sysMenu.setParentName("一级菜单");
                } else {
                    SysMenu s = sysMenuService.queryById(String.valueOf(sysMenu.getParentId()));
                    sysMenu.setParentName(s.getName());
                }
            }
        } catch (Exception e) {
            return Result.error(MenuErrorCode.SYS_MENU_SELECT_ERROR_CODE, MenuErrorCode.SYS_MENU_SELECT_ERROR_MESSAGE);
        }

        return Result.success(result.getTotal(), result.getList());
    }

    /**
     * 根据id查询菜单信息
     *
     * @param id 菜单ID
     * @return 菜单实体
     */
    @RequestMapping("/{id}")
    public Result queryById(@PathVariable String id) {
        SysMenu model = null;
        try {
            // 校验参数
            if (ObjectUtils.isEmpty(id)) {
                return Result.error(MenuErrorCode.SYS_MENU_PARAMS_ERROR_CODE, MenuErrorCode.SYS_MENU_PARAMS_ERROR_MESSAGE);
            }
            model = sysMenuService.queryById(id);
            if (ObjectUtils.isEmpty(model)) {
                model = new SysMenu();
            }
        } catch (Exception e) {
            return Result.error();
        }

        return Result.success().put("model", model);
    }

    /**
     * 保存
     *
     * @param sysMenu 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysMenu sysMenu) {

        try {
            // 校验参数
            if (ObjectUtils.isEmpty(sysMenu)) {
                return Result.error(MenuErrorCode.SYS_MENU_PARAMS_ERROR_CODE, MenuErrorCode.SYS_MENU_PARAMS_ERROR_MESSAGE);
            }
            // 执行入库操作
            sysMenuService.insert(sysMenu);
        } catch (ApplicationException e) {
            return Result.error(RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(RoleErrorCode.SYS_ROLE_SAVE_ERROR_CODE, RoleErrorCode.SYS_ROLE_SAVE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 修改操作
     *
     * @param sysMenu 请求数据对象
     * @return 结果对象
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {

        try {
            // 校验参数
            if (ObjectUtils.isEmpty(sysMenu)) {
                return Result.error(MenuErrorCode.SYS_MENU_PARAMS_ERROR_CODE, MenuErrorCode.SYS_MENU_PARAMS_ERROR_MESSAGE);
            }
            // 执行修改
            sysMenuService.update(sysMenu);
        } catch (RuntimeException e) {
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_ERROR_MESSAGE);
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
    @Transactional(rollbackFor = Exception.class)
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {
            // 校验参数
            if (ObjectUtils.isEmpty(ids)) {
                return Result.error(MenuErrorCode.SYS_MENU_PARAMS_ERROR_CODE, MenuErrorCode.SYS_MENU_PARAMS_ERROR_MESSAGE);
            }
            for (String id : ids) {
                SysMenu s = sysMenuService.queryById(id);
                if (s.getParentId() != 0) {
                    sysMenuService.delete(Integer.valueOf(id));
                }else if(s.getParentId()==0){
                    List<Integer> sm = sysMenuService.queryByParentId(Integer.valueOf(id));
                    for(Integer i:sm){
                        sysMenuService.delete(i);
                    }
                    sysMenuService.delete(Integer.valueOf(id));
                }
            }

        } catch (ApplicationException e) {
            return Result.error(MenuErrorCode.SYS_MENU_DELETE_APP_ERROR_CODE, MenuErrorCode.SYS_MENU_DELETE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            return Result.error(MenuErrorCode.SYS_MENU_DELETE_ERROR_CODE, MenuErrorCode.SYS_MENU_DELETE_ERROR_MESSAGE);
        }

        return Result.success();
    }

    /**
     * 查询所有的菜单
     *
     * @return
     */
    @RequestMapping("/queryAllMenus")
    public List<ZTree> queryAllMenus() {
        List<SysMenu> sysMenus = null;
        List<ZTree> zTreeList = new ArrayList<>();
        try {
            sysMenus = sysMenuService.queryAllMenu();

            for (SysMenu sysMenu : sysMenus) {
                if (ObjectUtils.isEmpty(sysMenu)) {
                    continue;
                }
                ZTree zTree = new ZTree();
                zTree.setMenuId(sysMenu.getId());
                zTree.setParentId(sysMenu.getParentId());
                zTree.setName(sysMenu.getName());
                zTreeList.add(zTree);
            }
        } catch (Exception e) {
            throw new RuntimeException("查询所有菜单出现异常");
        }
        return zTreeList;
    }

    /**
     * 查询所有菜单(新增角色时用)
     *
     * @return
     */
    @RequestMapping("/queryAllMenuInsert")
    public Result queryAllMenu() {
        List<ZTree> treeList = new ArrayList<>();
        List<SysMenu> allMenuList = null;
        try {
            //查询列表数据
            allMenuList = sysMenuService.queryAllMenu();

            if (!ObjectUtils.isEmpty(allMenuList)) {
                for (SysMenu menu : allMenuList) {
                    ZTree tree = new ZTree();
                    tree.setMenuId(menu.getId());
                    tree.setParentId(menu.getParentId());
                    tree.setName(menu.getName());
                    treeList.add(tree);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("新增角色时查询所有菜单出现异常");
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

            if (!ObjectUtils.isEmpty(roleList)) {
                if (!ObjectUtils.isEmpty(allMenuList)) {
                    for (SysMenu sysMenu : allMenuList) {
                        //该用户有权限设置选中属性
                        if (roleList.contains(sysMenu.getId() + "")) {

                            ZTree zTree = new ZTree();

                            zTree.setMenuId(sysMenu.getId());
                            zTree.setParentId(sysMenu.getParentId());
                            zTree.setName(sysMenu.getName());
                            zTree.setChecked(true);
                            zTreeList.add(zTree);
                        } else {
                            ZTree zTree = new ZTree();

                            zTree.setMenuId(sysMenu.getId());
                            zTree.setParentId(sysMenu.getParentId());
                            zTree.setName(sysMenu.getName());
                            zTree.setChecked(false);
                            zTreeList.add(zTree);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("查询所有菜单更新出现异常");
        }

        return Result.success().put("model", zTreeList);
    }


}
