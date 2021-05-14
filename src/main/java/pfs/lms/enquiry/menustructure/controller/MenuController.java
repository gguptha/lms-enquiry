package pfs.lms.enquiry.menustructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.menustructure.domain.Menu;
import pfs.lms.enquiry.menustructure.domain.MenuHeader;
import pfs.lms.enquiry.menustructure.domain.MenuItem;
import pfs.lms.enquiry.menustructure.dto.ChildrenDTO;
import pfs.lms.enquiry.menustructure.dto.MenuDTO;
import pfs.lms.enquiry.menustructure.dto.MenuHeaderDTO;
import pfs.lms.enquiry.menustructure.service.IMenuService;

/**
 * Created by sajeev on 14-May-21.
 */

@ApiController
@RequiredArgsConstructor
public class MenuController {



    @Autowired
    private IMenuService menuService;


    @GetMapping("/menu")
    public ResponseEntity getMenuforUserRole(@RequestParam(value = "userRole", required = true) String userRole){


        Menu menu = menuService.findByUserRole(userRole);

        MenuDTO menuDTO = convertToDTO(menu);


        return ResponseEntity.ok(menuDTO);

    }


    private MenuDTO convertToDTO( Menu menu){

        MenuDTO menuDTO  = new MenuDTO();

        for(MenuHeader menuHeader: menu.getMenuHeaders()) {

            MenuHeaderDTO menuHeaderDTO = new MenuHeaderDTO();

                menuHeaderDTO.setId(menuHeader.getId());
                menuHeaderDTO.setTitle(menuHeader.getTitle());
                menuHeaderDTO.setTranslate(menuHeader.getTranslate());
                menuHeaderDTO.setType(menuHeader.getType());
                menuHeaderDTO.setIcon(menuHeader.getIcon());

            for (MenuItem menuItem : menuHeader.getMenuItems()) {
                    ChildrenDTO children  = new ChildrenDTO();

                    children.setId(menuItem.getId());
                    children.setTitle(menuItem.getTitle());
                    children.setIcon(menuItem.getIcon());
                    children.setType(menuItem.getType());
                    children.setTranslate(menuItem.getTranslate());
                    children.setUrl(menuItem.getUrl());

                    menuHeaderDTO.addChildren(children);
                }

             menuDTO.addMenuHeader(menuHeaderDTO);

        }
        return menuDTO;
    }
}
