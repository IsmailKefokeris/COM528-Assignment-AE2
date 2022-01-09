
# Use Cases Text

| Use cases   	|
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




























