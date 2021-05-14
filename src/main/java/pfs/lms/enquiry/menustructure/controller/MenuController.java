package pfs.lms.enquiry.menustructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pfs.lms.enquiry.config.ApiController;
import pfs.lms.enquiry.menustructure.domain.Menu;
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

        return ResponseEntity.ok(menu);

    }
}
