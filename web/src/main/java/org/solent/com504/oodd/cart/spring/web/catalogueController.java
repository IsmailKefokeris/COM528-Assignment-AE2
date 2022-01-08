package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Address;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class catalogueController {

    final static Logger LOG = LogManager.getLogger(catalogueController.class);
    
    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;
    
    @Autowired
    ShoppingService shoppingService = null;

    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser", sessionUser);
        }
        return sessionUser;
    }
    

    @RequestMapping(value = {"/modifyItem"}, method = RequestMethod.GET)
    public String modifyitem(
            @RequestParam(value = "itemID", required = true) long itemID,
            Model model,
            HttpSession session) {
        String message = "";
        String errorMessage = "";

        model.addAttribute("selectedPage", "admin");

        LOG.debug("get viewModifyItem called for Item=" + itemID);

        // check secure access to modifyUser profile
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "viewModifyItem";
        }
        
        try {
            
            Optional<ShoppingItem> modifyItem = shoppingItemCatalogRepository.findById(itemID);
            ShoppingItem foundItem = modifyItem.get();
            
            LOG.info("ITEMS: " + foundItem);
            model.addAttribute("modifyItem", foundItem);
            
            message = foundItem.getName() + " Has been Found!";
            
            model.addAttribute("message", message);
            model.addAttribute("errorMessage", errorMessage);
            
            return "viewModifyItem";
        } catch(Exception e) {
            LOG.error("Unable to Find Item" + itemID);
            return ("catalogue");
        }

    }
    
    @RequestMapping(value = {"/removeItem"}, method = RequestMethod.GET)
    public String removeitem(
            @RequestParam(value = "itemID", required = true) long itemID,
            Model model,
            HttpSession session) {
        
        String message = "";
        String errorMessage = "";
        
        model.addAttribute("selectedPage", "admin");
        
        // check secure access to modifyUser profile
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (UserRole.ANONYMOUS.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be logged in to access user information";
            model.addAttribute("errorMessage", errorMessage);
            return "catalogue";
        }
        
        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "catalogue";
        }
        
        Optional<ShoppingItem> modifyItem = shoppingItemCatalogRepository.findById(itemID);
        ShoppingItem foundItem = modifyItem.get();
        try {

            message = "Item " + foundItem.getName() + " Removed Successfully";
            
            shoppingItemCatalogRepository.delete(foundItem);
            model.addAttribute("message", message);
        } catch (Exception e) {
            errorMessage = "Error: Unable to remove Item: " + foundItem.getName();
            model.addAttribute("errorMessage", errorMessage);
        }
        
        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();
        
        
        // populate model with values
        model.addAttribute("availableItems", availableItems);
                
        return "catalogue";
    }

    @RequestMapping(value = {"/modifyItem"}, method = RequestMethod.POST)
    public String updateitem(
            @RequestParam(value = "itemID", required = true) long itemID,
            @RequestParam(value = "name", required = true) String newName,
            @RequestParam(value = "price", required = true) String price,
            Model model,
            HttpSession session) {
        String message = "";
        String errorMessage = "";

        LOG.debug("post updateItem called for ItemID=" + itemID);

        // security check if party is allowed to access or modify this party
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "viewModifyUser";
        }
        
        Optional<ShoppingItem> modifyItem = shoppingItemCatalogRepository.findById(itemID);
        ShoppingItem foundItem = modifyItem.get();
        
        
        if (newName != null) {
            foundItem.setName(newName);
        }
        if (price != null) {
            double num = Double.parseDouble(price);
            foundItem.setPrice(num);
        }
        
        shoppingItemCatalogRepository.save(foundItem);
        

        model.addAttribute("modifyItem", foundItem);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", "User " + foundItem.getName()+ " updated successfully");

        model.addAttribute("selectedPage", "admin");

        return "viewModifyItem";
    }

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model,
            HttpServletRequest request
    ) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for sessionUser
    }

}
