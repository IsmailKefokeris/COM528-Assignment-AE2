package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cardcheck.CardValidationResult;
import org.solent.com504.oodd.cardcheck.RegexCardValidator;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.CardDetails;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.web.WebObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MVCController {

    final static Logger LOG = LogManager.getLogger(MVCController.class);

    // this could be done with an autowired bean
    //private ShoppingService shoppingService = WebObjectFactory.getShoppingService();
    @Autowired
    ShoppingService shoppingService = null;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Autowired
    private UserRepository userRepository;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;

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

    // this redirects calls to the root of our application to index.html
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewHome(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemID", required = false) Long itemID,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "home");

        String message = "";
        String errorMessage = "";

        // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
        if (action == null) {
            // do nothing but show page
        } else if ("addItemToCart".equals(action)) {
            Optional<ShoppingItem> optional = shoppingItemCatalogRepository.findById(itemID);
            ShoppingItem foundItem = optional.get();

            if (foundItem == null) {
                message = "cannot add unknown " + itemID + " to cart";
            } else {
                message = "adding " + foundItem.getName() + " to cart price= " + foundItem.getPrice();
                shoppingCart.addItemToCart(foundItem);
            }
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "home";
    }

    @RequestMapping(value = "/catalogue", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCatalogue(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemID", required = false) String itemID,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "admin");

        String message = "";
        String errorMessage = "";

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "catalogue";
        }

        // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
        if (action == null) {

        }
        // do nothing but show page
        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "catalogue";
    }

    @RequestMapping(value = "/viewModifyItem", method = {RequestMethod.GET})
    public String modifyItem(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemID", required = false) Long itemID,
            Model model,
            HttpSession session) {

        String message = "";
        String errorMessage = "";

        // security check if party is allowed to access or modify this party
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "admin");

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "viewModifyItem";
        }

        Optional<ShoppingItem> optional = shoppingItemCatalogRepository.findById(itemID);
        ShoppingItem foundItem = optional.get();

        if ("modifyItem".equals(action)) {
            model.addAttribute("modifyItem", foundItem);
            return "viewModifyItem";

        } else if ("removeItem".equals(action)) {
            List<ShoppingItem> availableItems = shoppingService.getAvailableItems();
            try {
                shoppingItemCatalogRepository.delete(foundItem);
            } catch (Exception e) {
                errorMessage = "Error in deleting " + foundItem.getName() + " :: " + e;
                model.addAttribute("errorMessage", errorMessage);
                return "catalogue";
            } finally {
                List<ShoppingItem> availableItems2 = shoppingService.getAvailableItems();
                message = "Delete of " + foundItem.getName() + " Successful!";
                model.addAttribute("message", message);
                model.addAttribute("availableItems", availableItems2);
                return "catalogue";
            }
        }
        errorMessage = "Error in deleting " + foundItem.getName();
        model.addAttribute("errorMessage", errorMessage);
        return "catalogue";
    }

    @RequestMapping(value = "/viewModifyItem", method = {RequestMethod.POST})
    public String modifyItem(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemID", required = false) Long itemID,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemPrice", required = false) Double itemPrice,
            Model model,
            HttpSession session) {

        String message = "";
        String errorMessage = "";

        // security check if party is allowed to access or modify this party
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "viewModifyItem";
        }

        model.addAttribute("selectedPage", "admin");

        Optional<ShoppingItem> optional = shoppingItemCatalogRepository.findById(itemID);
        ShoppingItem foundItem = optional.get();

        try {
            foundItem.setName(itemName);
            foundItem.setPrice(itemPrice);
            message = foundItem.getName() + " Updated Successfully";
            model.addAttribute("message", message);
            model.addAttribute("modifyItem", foundItem);
            shoppingItemCatalogRepository.save(foundItem);
            return "viewModifyItem";
        } catch (Exception e) {
            errorMessage = "Error in Updating " + foundItem.getName();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("modifyItem", foundItem);
            return "viewModifyItem";
        }
    }

    @RequestMapping(value = "/cart", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCart(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemID", required = false) Long itemID,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        String message = "";
        String errorMessage = "";

        if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            Optional<ShoppingItem> optional = shoppingItemCatalogRepository.findById(itemID);
            ShoppingItem foundItem = optional.get();

            shoppingCart.removeItemFromCart(foundItem.getUuid());

        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();

        // used to set tab selected
        model.addAttribute("selectedPage", "cart");
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);
        return "cart";
    }

    @RequestMapping(value = "/checkout", method = {RequestMethod.GET})
    public String viewCheckout(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "cardNum", required = false) String cardNum,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "issueNum", required = false) String issueNum,
            @RequestParam(name = "cvv", required = false) String cvv,
            Model model,
            HttpSession session) {

        String message = "";
        String errorMessage = "";
        CardDetails savedCardDetails = null;

        try {
            User sessionUser = getSessionUser(session);
            Optional<User> optional = userRepository.findById(sessionUser.getId());
            User foundUser = optional.get();
            savedCardDetails = foundUser.getCardDetails();

        } catch (InvalidDataAccessApiUsageException e) {
            errorMessage = "Must be Signed in to Checkout!!...";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/login";
        }
        model.addAttribute("selectedPage", "cart");
        model.addAttribute("savedCardDetails", savedCardDetails);
        model.addAttribute("message", message);

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = {RequestMethod.POST})
    public String checkout(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "cardNum", required = false) String cardNum,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "issueNum", required = false) String issueNum,
            @RequestParam(name = "cvv", required = false) String cvv,
            Model model,
            HttpSession session) {
        String message = "";
        String errorMessage = "";
        CardDetails savedCardDetails = null;

        try {
            User sessionUser = getSessionUser(session);
            Optional<User> optional = userRepository.findById(sessionUser.getId());
            User foundUser = optional.get();
            savedCardDetails = foundUser.getCardDetails();
            
            List<User> adminOptional = userRepository.findByUsername("globaladmin");
            User adminUser = adminOptional.get(0);
            
            LOG.debug(adminUser.getCardDetails());
            
//            Shopping Cart
            List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
            Double shoppingcartTotal = shoppingCart.getTotal();
            
            

            if ("continue".equals(action)) {
                LOG.debug("CARDNUM: " + cardNum);
                CardValidationResult result = RegexCardValidator.isValid(cardNum);
                if (result.isValid()) {
                    
                    try {
                        CardDetails newCard = new CardDetails();
                        newCard.setCardNumber(cardNum);
                        newCard.setCvv(cvv);
                        newCard.setEndDate(endDate);
                        newCard.setIssueNumber(issueNum);
                        foundUser.setCardDetails(newCard);
                        
                        shoppingService.purchaseItems(shoppingCart, foundUser, newCard, adminUser.getCardDetails());
                        
                    } catch (Exception e) {
                        
                    }

                }

            }

//            message = "USER: " + foundUser.getFirstName();
        } catch (InvalidDataAccessApiUsageException e) {
            errorMessage = "Must be Signed in to Checkout!!...";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/login";
        }

        model.addAttribute("selectedPage", "cart");
        model.addAttribute("savedCardDetails", savedCardDetails);
        model.addAttribute("message", message);

        return "checkout";
    }
    
    @RequestMapping(value = "/viewInvoices", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewInvoices(@RequestParam(name = "username", required = false) String username,
            Model model, 
            HttpSession session) {
        String message = "";
        String errorMessage = "";


        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);
        
        LOG.debug("Mapping recieved.");
        
        if (!UserRole.ADMINISTRATOR.equals(sessionUser.getUserRole())) {
            errorMessage = "you must be an administrator to access users information";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/home";
        }
        
        List<User> optional = userRepository.findByUsername(username);
        User foundUser = optional.get(0);
        
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<Invoice> userSpecificList = null;
        
        for (Invoice invoice : invoiceList) {
            if (invoice.getPurchaser().equals(foundUser)) {
                userSpecificList.add(invoice);
            }
        }
        
        

        // used to set tab selected
        model.addAttribute("selectedPage", "about");
        model.addAttribute("invoiceList", userSpecificList);

        return "viewInvoices";
    }

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String aboutCart(Model model, HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "about");
        return "about";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contactCart(Model model, HttpSession session) {

        // get sessionUser from session
        User sessionUser = getSessionUser(session);
        model.addAttribute("sessionUser", sessionUser);

        // used to set tab selected
        model.addAttribute("selectedPage", "contact");
        return "contact";
    }


    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
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
