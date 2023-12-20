import {Button, Col, Form, Row} from "react-bootstrap";

function CompanyAddForm({onSubmit, initialValue, edit, onChange}){
    return (
        <div className="formBox">
        <Form onSubmit={onSubmit}>
            <Row>
                <Form.Group id="name" as={Col}>
                    <Form.Label>Company Name</Form.Label>
                    <Form.Control type="text" name="name" value={initialValue.name} onChange={onChange}/>
                </Form.Group>
                <Form.Group id="currency" as={Col}>
                    <Form.Label>Currency</Form.Label>
                    <Form.Select name="currency" value={initialValue.currency} onChange={onChange}>
                        <option value="USD">USD</option>
                        <option value="CNY">CNY</option>
                    </Form.Select>
                </Form.Group>
                <Form.Group id="defaultDiscount" as={Col}>
                    <Form.Label>Default Price</Form.Label>
                    <Form.Control type="number" name="defaultDiscount" value={initialValue.defaultDiscount} onChange={onChange}/>
                </Form.Group>
            </Row>
            <Button type="submit" className="mt-4" variant="secondary">{edit? '수정하기' : '추가하기'}</Button>
        </Form>
        </div>
    );
}

export default CompanyAddForm;