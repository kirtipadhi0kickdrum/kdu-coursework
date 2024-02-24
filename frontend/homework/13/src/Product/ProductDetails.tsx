import { useContext } from "react"
import { ProductContext } from "../App"
import { BrowserRouter, Link, Routes, useParams } from "react-router-dom"
import './ProductsDetails.scss'

export function ProductDetails() {

    const {productData} = useContext(ProductContext)
    const { productId} = useParams()

    const product = productData.find((prod) => prod.id === parseInt(productId, 10))

    if(!product)
    {
        return <div>Product not found</div>
    }

  return (
    <div id="product-details">
        <div id="product-title-div">
            <h1>{product.title}</h1>
        </div>
        <div id="product-description">
            <img src={`${product.image}`} alt=""  id="prod-image"/>
            <div id="prod-details">
                <p id="prod-title">Model: {product.title}</p>
                <p id="prod-price">Price: ${product.price}</p>
                <h6>Product Description</h6>
                <p id="prod-description">
                    {product.description}
                </p>
                <Link to="/" id="back-to-list-link">
                        <button id="back-to-list-button">
                            Back to Products
                        </button>
                    </Link>
               
            </div>
        </div>

    </div>
  )
}
