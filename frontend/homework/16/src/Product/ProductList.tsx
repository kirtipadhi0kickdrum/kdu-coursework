import { useContext } from 'react'
import { ProductContext } from '../App'
import './ProductList.scss'
import { Link } from 'react-router-dom'

export function ProductList() {
    const products = useContext(ProductContext)


  return (
    <div id='product-container'>
        {products.productData.map((prod) => (
            <Link to={`/product/${prod.id}`} key={prod.id}>
            <div id='product-card' key={prod.id}>
                <img src={`${prod.image}`} alt="" id='product-image' />
                <div id="detail-display">
                    <p id='prod-title'>{prod.title}</p>
                    <p id='prod-price'><i>${prod.price}</i></p>
                </div>
            </div>
            </Link>
        ))}
    </div>
        
    
  )
}
