1、购物车列表

```
GET localhost:8181/cart
```

参数

```json
无
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "items": [
            {
                "cart_id": 3,
                "item_id": 1,
                "brand": "Nike",
                "name": "Air VaporMax EVO NRG",
                "img": "../static/air-vapormax-evo-nrg-0.jpg",
                "price": 1499.00,
                "num": 10,
                "valid": true
            },
            {
                "cart_id": 5,
                "item_id": 4,
                "brand": "Nike",
                "name": "Sportswear",
                "img": "../static/sportswear-0.jpg",
                "price": 299.00,
                "num": 1,
                "valid": true
            }
        ]
    }
}
```

2、添加购物车

```
POST localhost:8181/cart/add?itemId=2
```

参数

```json
itemId: 2(example)
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
或
{
    "code": 1,
    "msg": "fail",
    "data": "该商品已在购物车中"
}
或
{
    "code": 1,
    "msg": "fail",
    "data": "库存不足"
}
```

3、删除购物车项目

```
PUT localhost:8181/cart/delete?cartId=3
```

参数

```json
cartId = 3 (example)
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "SUCCESS"
}
```

4、修改商品数量

```
PUT localhost:8181/cart/update?cartId=3&num=5
```

参数

```json
cartid = 3, num = 5
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
或
{
    "code": 1,
    "msg": "fail",
    "data": "库存不足"
}
```

5、添加收货信息

```
POST localhost:8181/payment/add
```

参数

```json
paymentDTO表单
属性
		private String familyName;
   	private String givenName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String tel;
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
```

6、展示所有收货信息

```
GET localhost:8181/payment
```

参数

```json
无
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "info": [
            {
                "payment_id": 3,
                "name": "321412343124",
                "address": "fdsaffdasfafdsafasdfasfa",
                "tel": "233223"
            },
            {
                "payment_id": 4,
                "name": "fassdfffff",
                "address": "ddddffffddddfdffa",
                "tel": "2323"
            }
        ]
    }
}
```

7、删除收货信息

```
PUT localhost:8181/payment/delete?paymentId=1
```

参数

```json
paymentId
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
```

8、选择收货信息（修改前选择要修改的信息框 返回该框里的信息）

```
GET localhost:8181/payment/choose?paymentId=1
```

参数

```json
paymentId
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "familyName": "fassdf",
        "givenName": "ffff",
        "province": "dddd",
        "city": "ffff",
        "district": "dddd",
        "address": "fdffa",
        "tel": "2323"
    }
}
```

9、修改收货信息

```
PUT localhost:8181/payment/update
```

参数

```json
paymentId  paymentDTO（属性见5）
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
```

10、购物车-》结算页 在结算页展示已经选定的商品

```
localhost:8181/payment/payingItems?cartIds=7,8
```

参数

```json
Integer 数组 cartIds
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "items": [
            {
                "cart_id": 7,
                "item_id": 2,
                "brand": "Nike",
                "name": "Kyrie 7 EP",
                "img": "../static/kyrie-7-ep-0.jpg",
                "current_price": 999.00,
                "previous_price": null,
                "group": "男子",
                "style": "篮球鞋",
                "color": "白色",
                "size": "44",
                "num": 1,
                "valid": true
            },
            {
                "cart_id": 8,
                "item_id": 10,
                "brand": "Nike",
                "name": "Printed Hoodie Sportswear",
                "img": "../static/sportswear-older-printed-hoodie-0.jpg",
                "current_price": 369.00,
                "previous_price": null,
                "group": "儿童",
                "style": "运动服",
                "color": "红色",
                "size": "L",
                "num": 5,
                "valid": true
            }
        ],
        "total_price": 2844.00
    }
}
```

11、下单

```
POST localhost:8181/order/create
```

参数

```json
orderDTO:
		private Integer[] cartIds;
    private Integer paymentId;
    private String deliveryType;
    private String paymentMethod;
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "order_id": 7,
        "is_paid": true,
        "payment_method": "支付宝",
        "total_price": 1140.00,
        "address": "北京市市辖区东城区sasa",
        "delivery_type": "普通达",
        "related_items": [
            {
                "id": 7,
                "brand": "Adidas",
                "name": "HER Studio London T-Shirt",
                "img": "../static/HER_Studio_London_T-Shirt-0.jpg",
                "price": 300.00,
                "previous_price": 320.00,
                "group": "女子",
                "style": "T恤"
            },
            {
                "id": 8,
                "brand": "Nike",
                "name": "Sportswear Essentials",
                "img": "../static/sportswear-essentials-backpack-0.jpg",
                "price": 520.00,
                "previous_price": 550.00,
                "group": "男子",
                "style": "包"
            },
            {
                "id": 9,
                "brand": "Nike",
                "name": "Sportswear Heritage",
                "img": "../static/sportswear-heritage-hip-pack-0.jpg",
                "price": 150.00,
                "previous_price": null,
                "group": "女子",
                "style": "包"
            },
            {
                "id": 0,
                "brand": "李宁",
                "name": "烈骏ACE 2减震跑鞋",
                "img": "../static/Lining-liejun-ACE-men-0.jpg",
                "price": 999.00,
                "previous_price": 1099.00,
                "group": "男子",
                "style": "跑鞋"
            },
            {
                "id": 1,
                "brand": "Nike",
                "name": "Air VaporMax EVO NRG",
                "img": "../static/air-vapormax-evo-nrg-0.jpg",
                "price": 1499.00,
                "previous_price": null,
                "group": "男子",
                "style": "休闲鞋"
            },
            {
                "id": 2,
                "brand": "Nike",
                "name": "Kyrie 7 EP",
                "img": "../static/kyrie-7-ep-0.jpg",
                "price": 999.00,
                "previous_price": null,
                "group": "男子",
                "style": "篮球鞋"
            }
        ]
    }
}
```

12、（补）清空购物车

```
PUT localhost:8181/cart/deleteAll
```

参数

```json
无
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
```

13、查看所有订单信息

```
GET localhost:8181/order/view
```

参数

```json
无
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "orders": [
            {
                "order_id": 3,
                "items": [
                    {
                        "item_id": 0,
                        "brand": "李宁",
                        "name": "烈骏ACE 2减震跑鞋",
                        "img": "../static/Lining-liejun-ACE-men-0.jpg",
                        "num": 1,
                        "current_price": 999.00
                    },
                    {
                        "item_id": 1,
                        "brand": "Nike",
                        "name": "Air VaporMax EVO NRG",
                        "img": "../static/air-vapormax-evo-nrg-0.jpg",
                        "num": 1,
                        "current_price": 1499.00
                    }
                ],
                "items_price": 2498.00,
                "delivery_type": "普通达",
                "delivery_price": 0.00,
                "total_price": 2498.00,
                "create_time": "01/05/2021 16:08",
                "arrival_time": "01/05/2021 16:08",
                "status": "派送中"
            },
            {
                "order_id": 4,
                "items": [
                    {
                        "item_id": 2,
                        "brand": "Nike",
                        "name": "Kyrie 7 EP",
                        "img": "../static/kyrie-7-ep-0.jpg",
                        "num": 1,
                        "current_price": 999.00
                    }
                ],
                "items_price": 999.00,
                "delivery_type": "普通达",
                "delivery_price": 0.00,
                "total_price": 999.00,
                "create_time": "01/05/2021 16:16",
                "arrival_time": "01/05/2021 16:16",
                "status": "派送中"
            }
        ]
    }
}
```

14、删除单条订单信息

```
PUT localhost:8181/order/delete
```

参数

```json
orderId 13中给出
```

返回

```json
{
    "code": 0,
    "msg": "success",
    "data": "成功"
}
```

