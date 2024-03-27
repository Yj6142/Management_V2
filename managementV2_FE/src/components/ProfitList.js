import CurrentDisplay from "./CurrentDisplay";
import {Table} from "react-bootstrap";

function ProfitList({data}) {
    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Order Date</th>
                    <th>Company Name</th>
                    <th>Total Profit</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr>
                            <td>{item.orderDate}</td>
                            <td>{item.companyName}</td>
                            <td><CurrentDisplay price={item.totalProfit} currency="KRW"/></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    );
}

export default ProfitList;