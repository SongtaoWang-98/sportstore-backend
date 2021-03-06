# 1、首页数据

```
GET /home
```

参数

```json
无
```

返回

```json
{
code: 0,
msg: "成功",
data: {
classification: [
{
name: "men",
categories: [
{
name: "shoes",
styles: [
"running shoes",
"slippers",
"basketball shoes"
]
}
]
},
{
name: "women",
categories: [
{
name: "clothing",
styles: [
"T-shirt",
"sweatshirts"
]
}
]
},
{
name: "kids",
categories: [
{
name: "shoes",
styles: [
"running shoes"
]
}
]
}
],
poster: "../static/poster1.jpg",
recommend: [
{
name: "Kyrie 7 EP",
img: "../static/kyrie-7-ep-0.jpg",
price: 999,
group: "men",
style: "basketball shoes"
},
{
name: "Tensaur Shoes Black",
img: "../static/Tensaur_Shoes_Black-0.jpg",
price: 210,
group: "kids",
style: "running shoes"
}
],
new: [
{
name: "Nike Sportswear",
img: "../static/sportswear-0.jpg",
price: 299,
group: "women",
style: "sweatshirts"
},
{
name: "Tensaur Shoes Black",
img: "../static/Tensaur_Shoes_Black-0.jpg",
price: 210,
group: "kids",
style: "running shoes"
},
{
name: "Nike Air Max 90 EOI",
img: "../static/air-max-90-eoi-0.jpg",
price: 620,
group: "kids",
style: "running shoes"
},
{
name: "Nike Air VaporMax EVO NRG",
img: "../static/air-vapormax-evo-nrg-0.jpg",
price: 1499,
group: "men",
style: "running shoes"
},
{
name: "HER Studio London T-Shirt",
img: "../static/HER_Studio_London_T-Shirt-0.jpg",
price: 300,
group: "women",
style: "T-shirt"
},
{
name: "Kyrie 7 EP",
img: "../static/kyrie-7-ep-0.jpg",
price: 999,
group: "men",
style: "basketball shoes"
}
],
trending: [
{
name: "HER Studio London T-Shirt",
img: "../static/HER_Studio_London_T-Shirt-0.jpg",
price: 300,
group: "women",
style: "T-shirt"
},
{
name: "Tensaur Shoes Black",
img: "../static/Tensaur_Shoes_Black-0.jpg",
price: 210,
group: "kids",
style: "running shoes"
},
{
name: "Nike Air Max 90 EOI",
img: "../static/air-max-90-eoi-0.jpg",
price: 620,
group: "kids",
style: "running shoes"
},
{
name: "Nike Air VaporMax EVO NRG",
img: "../static/air-vapormax-evo-nrg-0.jpg",
price: 1499,
group: "men",
style: "running shoes"
},
{
name: "Nike Sportswear",
img: "../static/sportswear-0.jpg",
price: 299,
group: "women",
style: "sweatshirts"
},
{
name: "Nike Benassi JDI Print",
img: "../static/benassi-jdi-print-0.jpg",
price: 299,
group: "men",
style: "slippers"
}
],
discount: [
{
name: "Nike Sportswear",
img: "../static/sportswear-0.jpg",
price: 299,
previousPrice: 499,
group: "women",
style: "sweatshirts"
},
{
name: "Tensaur Shoes Black",
img: "../static/Tensaur_Shoes_Black-0.jpg",
price: 210,
previousPrice: 240,
group: "kids",
style: "running shoes"
},
{
name: "HER Studio London T-Shirt",
img: "../static/HER_Studio_London_T-Shirt-0.jpg",
price: 300,
previousPrice: 320,
group: "women",
style: "T-shirt"
}
],
group: [
{
name: "men",
img: "../static/home-group-men.jpg"
},
{
name: "women",
img: "../static/home-group-women.jpg"
},
{
name: "kids",
img: "../static/home-group-kids.jpg"
}
]
}
}
```


