package pfs.lms.enquiry.menustructure.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.menustructure.domain.Menu;
import pfs.lms.enquiry.menustructure.domain.MenuHeader;
import pfs.lms.enquiry.menustructure.domain.MenuItem;
import pfs.lms.enquiry.menustructure.repository.MenuHeaderRepository;
import pfs.lms.enquiry.menustructure.repository.MenuItemRepository;
import pfs.lms.enquiry.menustructure.repository.MenuRepository;
import pfs.lms.enquiry.menustructure.service.IMenuService;

import java.util.Optional;

/**
 * Created by sajeev on 14-May-21.
 */
@Slf4j
@Service
@RequiredArgsConstructor
 public class MenuService implements IMenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuHeaderRepository menuHeaderRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public Menu createMenu(Menu menu) {

        menu = menuRepository.save(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {

        Optional<Menu> existingMenuOptional = menuRepository.findById(menu.getIdentifier());
        Menu existingMenu = existingMenuOptional.get();


        //Delete the existing Header and Items
//        if (existingMenu.getMenuHeaders() != null){
//            for (MenuHeader menuHeader : existingMenu.getMenuHeaders()) {
//                for (MenuItem menuItem : menuHeader.getMenuItems()) {
//                    menuItemRepository.deleteById(menuItem.getIdentifier());
//                }
//                menuHeaderRepository.deleteById(menuHeader.getIdentifier());
//            }
//        }

        menu = menuRepository.save(menu);
        return menu;
    }

    @Override
    public Menu findByUserRole(String userRole) {

        Menu menu = menuRepository.findByUserRole(userRole);
        return menu;
    }
}
