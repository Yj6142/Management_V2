import {Table} from "react-bootstrap";
import {useState} from "react";
import EditModal from "./modals/EditModal";
import CurrentDisplay from "./CurrentDisplay";

function ItemTable({data, editData, handleSubmit}){

    const [show, setShow] = useState(false);
    const [inputPrice, setInputPrice] = useState(0);
    const [editId, setEditId] = useState(0);

    const handleShow = (id) => {
        setShow(true);
        setEditId(id);
        const editItem = data.find(n => n.id === id);
        setInputPrice(editItem.exPrice);
    }

    const handleChange = (e) => {
        setInputPrice(e.target.value);
    }

    const handleClose = () => setShow(false);

    const handleEdit = () => {
        setShow(false);
        editData({editId, inputPrice});
    }


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
                            <td><CurrentDisplay currency="KRW" price={item.price}/></td>
                            <td>
                                <div style={{display: 'inline-flex'}}>
                                    <CurrentDisplay currency={item.currencyCode} price={item.exPrice}/>
                                    <button onClick={() => {
                                        handleShow(item.id);
                                    }}>✏️</button>
                                </div>
                            </td>
                            <td><button onClick={()=>{
                                handleSubmit(item.id);
                            }}>✚</button></td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
            <EditModal show={show}
                       handleChange={handleChange}
                       handleEdit={handleEdit}
                       inputPrice={inputPrice}
                       handleClose={handleClose}/>
        </div>
    );

}

export default ItemTable;