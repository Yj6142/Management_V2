import {Button, Col, Form, Row} from "react-bootstrap";
import ItemBox from "../components/ItemBox";

function QuotationPage() {
    return (
        <div>
            <h2 className="p-2">Quotation</h2>
            <Form className="p-2">
                <Row>
                    <Col xs="auto">
                        <Form.Select>
                            <option>select company ...</option>
                            <option value="1">cnc</option>
                            <option value="2">ks</option>
                        </Form.Select>
                    </Col>
                    <Col>
                        <Button>선택</Button>
                    </Col>
                </Row>
            </Form>
            <ItemBox></ItemBox>
        </div>
    );
}

export default QuotationPage;