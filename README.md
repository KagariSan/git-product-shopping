Shop Product.
Product -> Cart -> Checkout (ten, sdt, dia chi)

```
Product
id
name
description
price
```

1. Setup connection vao DB (ConnectionUtils) Blocker
2. DAO - Model  (Spring -> Repository - Entity) Blocker
3. Servlet -> List
4. Servlet -> Add
5. Servlet -> Edit
6. Servlet -> Delete
```
Order
id
cus_name
cus_addr
cus_phone
total_price

OrderItem
order_id
product_id
name
description
price
quantity
```
7. Servlet -> AddItemToCart (DB, Session)
8. Servlet -> ViewCart
9. Servlet -> DeleteItemFromCart
8. Servlet -> Checkout
