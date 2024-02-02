import {Button, Table} from "react-bootstrap";
import {useState} from "react";
import EditModal from "./EditModal";

function ItemTable({data}){

    const [show, setShow] = useState(false);
    const [existingPrice, setExistingPrice] = useState(0);
    const handleShow = (id) => {
        setShow(true);
        const editItem = data.find(n => n.id === id);
        setExistingPrice(editItem.exPrice);
    }
    const handleClose = () => setShow(false);

    return (
        <div>
            <Table striped hover bordered>
                <thead>
                <tr>
                    <th>Article Num</th>
                    <th>Name</th>
                    <th>Im Price</th>
                    <th>Ex Price</th>
                    <th>추가</th>
                </tr>
                </thead>
                <tbody>
                {
                    data.map(item => (
                        <tr key={item.id}>
                            <td>{item.articleNum}</td>
                            <td>{item.name}</td>
                            <td>{item.price}</td>
                            <td>{item.exPrice} <Button onClick={()=>{
                                handleShow(item.id);
                            }}>수정</Button></td>
                            <td><Button>추가</Button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
            <EditModal show={show} handleClose={handleClose} existingPrice={existingPrice}></EditModal>
        </div>
    );

}

export default ItemTable;