import DatePicker from "react-datepicker";
import {useState} from "react";

import "react-datepicker/dist/react-datepicker.css";
import {Button} from "react-bootstrap";
function CalendarBox({onSubmit}) {

    const [startDate, setStartDate] = useState(new Date());

    const handleSelect = () => {
        onSubmit(startDate);
    }

    return (
        <div>
            <DatePicker
                showIcon
                dateFormat="yyyy/MM/dd"
                selected={startDate}
                onChange={(date) => setStartDate(date)}
                form="external-form"
            />
            <Button onClick={handleSelect}>선택</Button>
        </div>

    );
}

export default CalendarBox;