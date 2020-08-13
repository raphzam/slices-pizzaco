Pizza Project

User Interactions

Public

    Load home page
    Browse menu of pre made pizzas (optional)
    Sign Up
	Create An Account
	Login

User 
        
        Create New Order (can be created by public user) [user id ==null, 
        loop through orders where id = null, id = currentUser];
        
        boolean Complete on order class; complete = false;
        when they're done adding pizzas, complete = true;
            send text
            generate receipt
	    Create New Pizza from Scratch
		display picture of pizza with current toppings
			calculate pizza price (subtotal)
	    Order pizza from pre made pizzas (optional)
        Order pizza from previous order (optional)

    Calculate Order Total
    Display Order Total

    Checkout (submit order)
	create account if not logged in
	generate itemized receipt [order > pizza > print all the ingredients]
		display date and time submitted
		display cost of additional toppings
		
	save order to customer’s history
    
    javascript pictures
	send email or text upon submission

Admin (3-4 pages)
	
	SEARCH BOX IN ADMIN PAGE    
        search customer by name [customer repository query]
            [query usertable for name]
                results page
                    customer name
                    email
                    order history
                        all the pizzas order
            
	generate list of customers [print all in user repository, role USER]
	    nav link to get to customer table
	    
	calculate top 3 pizza toppings 
	    (dashboard view)
	    link to 
	        [pizza_ingredient_table, calculate top 3 ingredients]
	        
    calculate total sales
        (dashboard view)
        link to
            [add up all totals from order table]
        
    add/remove ingredients from inventory [add ingredient form, name, selection option for type, media upload]
        link to inventory management?
        
        
        
        
        
        
    add pre made pizzas to menu (optional)
