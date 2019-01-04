
package cn.com.njdhy.muscle.biceps.controller.sys;

import cn.com.njdhy.muscle.biceps.controller.Query;
import cn.com.njdhy.muscle.biceps.controller.Result;
import cn.com.njdhy.muscle.biceps.exception.ApplicationException;
import cn.com.njdhy.muscle.biceps.exception.sys.RoleErrorCode;
import cn.com.njdhy.muscle.biceps.model.SysMenu;
import cn.com.njdhy.muscle.biceps.model.SysRole;
import cn.com.njdhy.muscle.biceps.model.ZTree;
import cn.com.njdhy.muscle.biceps.service.sys.SysMenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
        Query queryParam = new Query(params);
        PageInfo<SysMenu> result = sysMenuService.queryList(queryParam, pageNumber, pageSize);

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

        SysMenu model = sysMenuService.queryById(id);

        if (ObjectUtils.isEmpty(model)) {
            model = new SysMenu();
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
            // 校验参数 todo

            // 执行入库操作
            sysMenuService.insert(sysMenu);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Result.error(RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_SAVE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
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
            // TODO: 2018/3/14

            // 执行修改
            sysMenuService.update(sysMenu);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Result.error(RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_QUERY_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Result deleteByIds(@RequestBody List<String> ids) {

        try {
            // 校验参数 todo
            sysMenuService.deleteByIds(ids);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Result.error(RoleErrorCode.SYS_ROLE_DELETE_APP_ERROR_CODE, RoleErrorCode.SYS_ROLE_DELETE_APP_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(RoleErrorCode.SYS_ROLE_DELETE_ERROR_CODE, RoleErrorCode.SYS_ROLE_DELETE_ERROR_MESSAGE);
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
            e.printStackTrace();
        }
        return zTreeList;
    }

    /**
     * 查询所有菜单(新增角色时用)
     * @return
     */
    @RequestMapping("/queryAllMenuInsert")
    public Result queryAllMenu(){

        List<ZTree> treeList = new ArrayList<>();
        //查询列表数据
        List<SysMenu> allMenuList = sysMenuService.queryAllMenu();

        if (!ObjectUtils.isEmpty(allMenuList)){
            for (SysMenu menu:allMenuList){
                ZTree tree = new ZTree();
                tree.setMenuId(menu.getId());
                tree.setParentId(menu.getParentId());
                tree.setName(menu.getName());
                treeList.add(tree);
            }
        }

        return Result.success().put("model", treeList);
    }

    @RequestMapping("queryAllMenuUpdate")
    public Result queryAllMenuUpdate(SysRole role){
        List<ZTree> zTreeList = new ArrayList<>();
        try {
            //查询列表数据
            List<SysMenu> allMenuList = sysMenuService.queryAllMenu();

            //根据角色ID查询该角色拥有的权限
            List<String> roleList = sysMenuService.queryMenuByRole(String.valueOf(role.getId()));

            if (!ObjectUtils.isEmpty(roleList)) {
                if (!ObjectUtils.isEmpty(allMenuList)) {
                    for ( SysMenu sysMenu : allMenuList ) {
                        //该用户有权限设置选中属性
                        if (roleList.contains(sysMenu.getId() + "")) {

                            ZTree zTree = new ZTree();

                            zTree.setMenuId(sysMenu.getId());
                            zTree.setParentId(sysMenu.getParentId());
                            zTree.setName(sysMenu.getName());
                            zTree.setChecked(true);
                            zTreeList.add(zTree);
                        }
                        else {
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
        }
        catch (Exception e) {
            throw e;
        }

        return Result.success().put("model", zTreeList);
    }


}
