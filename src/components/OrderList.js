import {Table} from "react-bootstrap";
import CurrentDisplay from "./CurrentDisplay";

function orderList({data}) {
    return(
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Order Date</th>
                    <th>Article Num</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Im price</th>
                    <th>Ex price</th>
                    <th>Profit</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td>{item.orderDate}</td>
                            <td>{item.articleNum}</td>
                            <td>{item.itemName}</td>
                            <td>{item.quantity}</td>
                            <td><CurrentDisplay price={item.imPrice} currency="KRW"/></td>
                            <td><CurrentDisplay price={item.exPrice} currency={item.currencyCode}/></td>
                            <td><CurrentDisplay price={item.profit} currency="KRW"/></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    )
}

export default orderList;
