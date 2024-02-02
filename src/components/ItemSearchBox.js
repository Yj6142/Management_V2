import {Button, Form, InputGroup} from "react-bootstrap";

function ItemSearchBox({onChange, onSearch, initialForm}){

    return (
        <div>
            <Form onSubmit={onSearch}>
                <InputGroup className="mb-3">
                    <Form.Select variant="outline-secondary" title="검색 방법" name="searchOption" onChange={onChange}
                                 value={initialForm.searchOption} id="input-group-dropdown-1">
                        <option value="1">Article Num</option>
                        <option value="2">name</option>
                    </Form.Select>
                    <Form.Control name="searchData" onChange={onChange} value={initialForm.searchData}/>
                    <Button type="submit">검색</Button>
                </InputGroup>
            </Form>
        </div>
    );

}

export default ItemSearchBox;