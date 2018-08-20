# Project component: shop-rating
Component of Cloud Project (UfPrCloud2);

Composed of:
- catalog (shop-catalog)
- purchase (shop-purchase)
- shop-rating
- shop-recommender
- registry
- gateway

## Mini API docs

**Set product rate**
```
POST /api/ratings/{productId}/{userId}
```
- Path Parameters: productId, userId
- Body Parameters: vote (1-5), comment
- **Return** Rate (JSON object)

**Get product rate by productId**
```
GET /api/ratings/{productId}
```
- Path Parameters: productId
- **Return** Product (JSON object)

**Get popular product**
```
GET /api/ratings/popular
```
- **Return** an array of ProductWithRate (JSON object) 
  - *ProductWithRate*: composed of productId, title, price, average of votes 
