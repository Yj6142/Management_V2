import {Pagination} from "react-bootstrap";

function PaginationBox(data) {

    const {totalPage, active, onChangePage} = data;

    let items = [];
    for(let num=1; num <= totalPage ; num++){
        items.push(
            <Pagination.Item key={num} active={num === active} onClick={()=>{
                onChangePage(num);
            }}>{num}</Pagination.Item>
        );
    }

    return (
        <div>
            <Pagination style={{justifyContent:'center'}}>{items}</Pagination>
        </div>
    );
}

export default PaginationBox;
