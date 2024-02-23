import {Button, Table} from "react-bootstrap";
import CurrentDisplay from "./CurrentDisplay";

function QuotationList({data}) {
    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Request Date</th>
                    <th>Article Num</th>
                    <th>Name</th>
                    <th>Ex Price</th>
                    <th>Im Price</th>
                    <th>Order Time</th>
                    <th>주문</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td className="col-md-2">{item.requestDate}</td>
                            <td className="col-md-2">{item.articleNum}</td>
                            <td className="col-md-4">{item.name}</td>
                            <td className="col-md-1"><CurrentDisplay price={item.exPrice} currency={item.currencyCode}/></td>
                            <td className="col-md-1"><CurrentDisplay price={item.imPrice} currency="KRW"/></td>
                            <td className="col-md-1">{item.orderTime}</td>
                            <td className="col-md-1"><Button>+</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    );
}

export default QuotationList;