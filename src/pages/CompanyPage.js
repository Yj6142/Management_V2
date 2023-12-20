import CompanyAddForm from "../components/CompanyAddForm";
import CompanyList from "../components/CompanyList";
import {useEffect, useState} from "react";
import {addCompany, deleteCompany, getCompanyList, updateCompanyList} from "../api/CompanyApi";
import './CompanyPage.css';

function CompanyPage() {
    const [companyList, setCompanyList] = useState([]);
    const [initialValue, setInitialValue] = useState({
        name : '',
        currency : '',
        defaultDiscount : '',
    })
    const [edit, setEdit] = useState(false);    //수정 상태인지 제출 상태인지 알려주는 state
    const [editNum, setEditNum] = useState();

    //초기 데이터 불러오기
    const handleLoadClick = async ()=> {
        try {
            const company = await getCompanyList();
            setCompanyList(company);
        } catch (e) {
            console.log("데이터를 불러오는데 오류가 발생했습니다.");
        }
    }

    //수정된 데이터 이용해서 새로 companyList 수정 & form 초기 데이터 세팅
    const updateCompany = (updateData) => {
        setCompanyList(prevCompanyList => {
            return prevCompanyList.map(item => {
                if(item.id === updateData.id) {
                    return {...item,
                        name : updateData.name,
                        currency : updateData.currency,
                        defaultDiscount : updateData.defaultDiscount
                    };
                }
                return item;
            })
        })
        setInitialValue({
            name: '',
            currency: '',
            defaultDiscount: '',
        });
    }

    //추가 버튼 클릭시 데이터 서버로 전송 + 수정 버튼 클릭시 서버로 수정된 데이터 전송 edit 모드 해제
    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);
        const submitData = {};

        formData.forEach((value, key) => {
            submitData[key] = value;
        })

        if(edit) {
            //수정 모드일 때 실행될 코드 수정!
            try{
                const updateCompanyData = await updateCompanyList(submitData,editNum);
                updateCompany(updateCompanyData);
                setEdit(false);
            } catch (e) {
                console.log("데이터를 수정하는데 오류가 발생했습니다.");
            }
        } else {
            try {
                const newCompany = await addCompany(submitData);
                setCompanyList(prevCompanyList => [...prevCompanyList, newCompany]);
            } catch (e) {
                console.log("데이터 업데이트 하는데 오류가 발생했습니다.");
            }
        }
    }

    //delete 버튼 클릭시 데이터 삭제
    const handleDelete = async (id) => {
        try{
            const deleteCompanyId = await deleteCompany(id);
            const afterDeleteCompany = companyList.filter(item => item.id !== deleteCompanyId);
            setCompanyList(afterDeleteCompany);
        } catch (e) {
            console.log("해당 데이터를 삭제하는데 오류가 발생했습니다.");
        }
    }

    //수정 버튼을 클릭하게 되면 id를 받아서 edit 하기 전 데이터 form 으로 전송 & 수정 모드로 변경
    const handleEdit = (id) => {
        const [editCompany] = companyList.filter(item => item.id === id);
        setInitialValue(prevValue => ({
            ...prevValue,
            name: editCompany.name,
            currency: editCompany.currency,
            defaultDiscount: editCompany.defaultDiscount,
        }));
        setEdit(true);
        setEditNum(id);
    }

    //입력 form 에 들어오는 데이터 변경될 때 마다 state 변경하면서 입력값 받아주기 (안하게 되면 form 태그 내 값 변경 X)
    const handleChange = (e) => {
        setInitialValue({
            ...initialValue,
            [e.target.name] : e.target.value,
        });
    }

    useEffect(() => {
        handleLoadClick();
    }, []);

    return(
        <div className="Container">
            <h2>Company List</h2>
            <CompanyAddForm
                            onSubmit={handleSubmit}
                            initialValue={initialValue}
                            edit={edit}
                            onChange={handleChange}
            />
            <CompanyList data={companyList}
                         onDelete={handleDelete}
                         onEdit={handleEdit}
            />
        </div>
    )
}

export default CompanyPage;