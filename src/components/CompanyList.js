import {Button, Table} from "react-bootstrap";

function CompanyList({data, onDelete, onEdit}) {
    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Currency</th>
                    <th>Default Price</th>
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
                            <td><Button variant="secondary" size="sm" onClick={()=>{
                                onEdit(item.id);
                            }}>EDIT</Button></td>
                            <td><Button variant="outline-danger" size="sm" onClick={()=>{
                                onDelete(item.id);
                            }}>DELETE</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </div>
    );
}

export default CompanyList;