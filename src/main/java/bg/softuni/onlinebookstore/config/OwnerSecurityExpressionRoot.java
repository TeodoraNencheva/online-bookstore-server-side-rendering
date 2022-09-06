package bg.softuni.onlinebookstore.config;

import bg.softuni.onlinebookstore.service.OrderService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private final Authentication authentication;
    private final OrderService orderService;
    private Object filterObject;
    private Object returnObject;


    public OwnerSecurityExpressionRoot(Authentication authentication, OrderService orderService) {
        super(authentication);
        this.authentication = authentication;
        this.orderService = orderService;
    }

    public boolean isOwner(Long id) {
        if (authentication.getPrincipal() == null) {
            return false;
        }

        String username = authentication.getName();

        return orderService.isOwner(username, id) || hasRole("ADMIN");
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
