import {Button, Table} from "react-bootstrap";
import {Link} from "react-router-dom";

function CompanyList({data, onDelete, onEdit}) {
    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Currency</th>
                    <th>Default Price</th>
                    <th>Quotation</th>
                    <th>Order</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td>{item.name}</td>
                            <td>{item.currency}</td>
                            <td>{item.defaultDiscount}</td>
                            <td><Link to={`/quotation/${item.name}`}>✚</Link> <Link to={`/quotations/${item.name}`}>📜</Link></td>
                            <td><Link to={`/orders/${item.name}`}>order</Link></td>
                            <td><Button variant="secondary" size="sm" onClick={()=>{
                                onEdit(item.id);
                            }}>EDIT</Button></td>
                            <td><Button variant="outline-danger" size="sm" onClick={()=>{
                                onDelete(item.id);
                            }}>❌</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    );
}

export default CompanyList;