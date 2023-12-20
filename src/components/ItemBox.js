import {Button, Col, Form, InputGroup, Row} from "react-bootstrap";
import './ItemBox.css';
function ItemBox(){
    return (
        <div className="ItemBox">
            <Form>
                <InputGroup className="mb-3">
                    <Form.Select variant="outline-secondary" title="검색 방법" id="input-group-dropdown-1">
                        <option value="1">description</option>
                        <option value="2">name</option>
                    </Form.Select>
                    <Form.Control aria-label="Text input with dropdown button"/>
                    <Button>검색</Button>
                </InputGroup>
                <Row className="mb-3">
                    <Form.Group as={Col} controlId="articleNum">
                        <Form.Label>✔️Article Num</Form.Label>
                        <Form.Control type="text" placeholder="Article Num" disabled readOnly/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>✔️Material Description</Form.Label>
                        <Form.Control type="text" placeholder="Material Description" disabled readOnly/>
                    </Form.Group>
                </Row>
                <Form.Group className="mb-3" controlId="imPrice">
                    <Form.Label>✔️Unit Price</Form.Label>
                    <Form.Control type="text" placeholder="Unit Pirce" disabled readOnly/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="exPrice">
                    <Form.Label>✔️Ex Price</Form.Label>
                    <Form.Control type="text" placeholder="Ex Price" disabled readOnly/>
                </Form.Group>
                <Button>추가</Button>
            </Form>
        </div>
    );

}

export default ItemBox;