import {Button, Table} from "react-bootstrap";
import CurrentDisplay from "./CurrentDisplay";

function QuotationTable ({data, handleDelete}) {
    return(
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Article Num</th>
                    <th>Name</th>
                    <th>Ex Price</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td>{item.articleNum}</td>
                            <td>{item.name}</td>
                            <td><CurrentDisplay currency={item.currencyCode} price={item.exPrice}/></td>
                            <td><Button onClick={()=>{
                                handleDelete(item.id);
                            }}>delete</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    )
}

export default QuotationTable;