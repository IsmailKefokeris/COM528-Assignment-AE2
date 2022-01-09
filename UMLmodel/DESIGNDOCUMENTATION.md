
# Use Cases Text

| Use case Num 	| Actor(s)   | Actions     | Reaction    |
| ----------- 	| ---------- | ----------- | ----------- |
| 1A 	| Anonymous, Customer, Admin | Actors open the shoppingCartApplication website. |  |
| 1B 	|  | | Home.jsp page will load up showing available items pulled from the database.  |
| 2A 	| Anonymous, Customer, Admin | Actors adds an item to their cart. |  |
| 2B 	|  | | +1 quantity of any given item will be added to the session cart.  |
| 3A 	| Anonymous, Customer, Admin | Actors view the cart.|  |
| 3B 	|  | | Cart.jsp page will be loaded, displaying all items added to cart  |
| 4A 	| Customer, Admin | Actors modify their own account details. |  |
| 4B 	|  | | Actors will be presented with a page allowing them to modify details  |
| 5A 	| Customer, Admin | Actors purchase cart items. (Successful) |  |
| 5B 	|  | | Actors will be prompted for bank details |
| 5C 	|  | Bank details inserted |  |
| 5D 	|  | |Actor details will be processed through the bank API. Cart emptied. Invoice created.  |
| 6A 	| Customer, Admin | Actors purchase cart items. (Unsuccessful) |  |
| 6B 	|  | |Actors will be prompted for bank details |
| 6C 	|  | Bank details inserted |  |
| 6D 	|  | |Actor details will be processed through the bank API. Will return them to cart with a failure message |
| 7A 	| Admin | Actor attempts to modify users |  |
| 7B 	|  | | Prompted with a page of user details. (Able to manipulate all) |
| 7C 	|  | Actor changes a variable and hits update. |  |
| 7D 	|  | |User is now updated with the newly input variables.  |
| 8A 	| Admin | Actor attempts to modify items from catalogue page |  |
| 8B 	|  | |Actor is provided with the selected items information. |
| 8C 	|  | Actor changes variable and hits update. |  |
| 8D 	|  | |Item updates and returns a successful message. |
| 9A 	| Admin, Customer | Actors attempt log-out |  |
| 9B 	|  | |Logout successful, user session turns to “Anonymous” |
| 10A	| Anonymous	| Actor attempts to checkout items in cart |  |	
| 10B	|		|  | Actor will be prompted with an error message to sign in first. |
| 11A	| Anonymous	| Actor attempts to create new account	|  |
| 11B	|		|  | Actor will be prompted to create a username and password |
| 11C |		| Details input successfully	|  |
| 11D |		|	 | Actor is now logged into the account they created. |


# Basic (General) Test Plan

This test Plan will include the three actors

`Anonymous`
`Admin`
`Customer`

| Test Plan	| 	Action	| 	Expected Result	| 	Result Given	| 	Pass or Fail	|
| ----------- 	| ---------- | ----------- | ----------- | ----------- |
| 1	| 	Anonymous User loads up home page | 	Home.jsp loads up displaying all items currently in the catalogue and their price.	| 	Same as Expected.	| 	Pass	| 
| 2	| 	Anonymous User adds any of the Items into cart | 	Message is displayed stating that the item has been successfully added and Item has been added to session cart.	| 	Same as Expected.	| 	Pass	| 
| 3	| 	Anonymous User clicks Login. | 	Displayed with the Login.jsp page allowing the Anonymous user to login	| 	Same as Expected.	| 	Pass	| 
| 4	| 	Anonymous User clicks Create new account | 	Displayed with the register.jsp page allowing the Anonymous user to create their unique account.	| 	Same as Expected.	| 	Pass	| 
| 5	| 	Anonymous user creates new account | 	Account created and saved into the database, then the user is redirected to userdetails page logged in. They should also retain their Cart.	| 	Same as Expected.	| 	Pass	| 
| 6	| 	Anonymous user logs into site using valid details | 	User will be logged in and taken to the home page.	| 	Same as Expected.	| 	Pass	| 
| 7	| 	Logged in User adds Item to cart. | 	Item should be added to users session cart.	| 	Same as Expected.	| 	Pass	| 
| 8	| 	User removes Item from cart | 	Item will be removed refreshing the page showing the updated cart.	| 	Same as Expected.	| 	Pass	| 
| 9	| 	Anonymous User attempts to checkout cart | 	Redirected back to cart with an error message telling them they need to login first	| 	Same as Expected.	| 	Pass	| 
| 10	| 	Logged in user attempts to checkout cart | 	Forwarded to the checkout items page allowing the user to input their card details for payment.	| 	Same as Expected.	| 	Pass	| 
| 11	| 	Continue checkout with incorrect card details | 	Redirected to checkout page with an error message letting the user know their details are not valid.	| 	Nothing happens except for a redirect to checkout page as there is an error on the backend.	| 	Fail	| 
| 12	| 	Continue checkout with valid card details | 	Redirected to empty cart with a message letting the user know the payment has gone through.	| 	Nothing happens except for a redirect to checkout page as there is an error on the backend.	| 	Fail	| 
| 13	| 	Admin User log in | 	A new header Item appears only for admin	| 	Same as Expected.	| 	Pass	| 
| 14	| 	Admin accessing manage Catalogue page | 	Catalogue.jsp is rendered allowing the admin access to modify or remove items from the website	| 	Same as Expected.	| 	Pass	| 
| 15	| 	Admin Modifies Item and updates it. | 	View Item modify page with open allowing the admin to make changes and will save after update is clicked, reloading the page with updated details.	| 	Same as Expected.	| 	Pass	| 
| 16	| 	Admin Removes Item. | 	Page reloaded after item is removed displaying only the remaining items from the catalogue.	| 	Same as Expected.	| 	Pass	| 
| 17	| 	Admin Modifies user account | 	Modify user account page loads up for admin with details of chosen user.	| 	Same as Expected.	| 	Pass	| 
| 18	| 	Admin views User invoices | 	Page opens displaying in rows the invoices from the specified user.	| 	Same as Expected.	| 	Pass	| 
| 19	| 	Anonymous or non admin user tries accessing catalogue page | 	Directed to an empty catalogue page with an error message saying they must be an admin.	| 	Same as Expected.	| 	Pass	| 
| 20	| 	Anonymous or non admin user tries accessing user modification page | 	Directed to empty page with an error message stating they must be logged in.	| 	Same as Expected.	| 	Pass	| 
| 21	| Non adming user tries accessing modification user page for another user | 	Redirects them with error message saying they must be logged into that user to modify it or be an admin.	| 	Same as Expected.	| 	Pass	| 


# Features for Each Role:

### Anonymous
  •	Any user who is viewing the site but has not logged in.
  •	Unable to checkout.
### Admin
  •	Able to add and remove and modify items from the catalogue. 
  •	Able to able to view and modify users in the system.
  •	Able to able to view and modify orders in the system.
### Customer
  •	Any user who has created an account and may have created orders.
### Deactivated
  •	A user whose account has been deactivated. This user cannot log into the system but details of previous orders will be retained for the administrator to see.






