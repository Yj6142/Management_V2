import {ListGroup} from "react-bootstrap";

function MenuList() {

    return (
        <ListGroup>
            <ListGroup.Item action href="/company" variant="light"> -> 회사 정보 등록하러 가기</ListGroup.Item>
            <ListGroup.Item action href="/company" variant="warning"> -> 견적서 작성 및 주문하러 가기</ListGroup.Item>
            <ListGroup.Item action href="/orders" variant="light"> -> 이윤 확인하러 가기</ListGroup.Item>
        </ListGroup>
    );
}

export default MenuList;