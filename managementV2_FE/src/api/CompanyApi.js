import axios from "axios";

const getCompanyList = async () => {
    try{
        const response = await axios.get("http://localhost:8080/company")
        return response.data;
    } catch(e){
        console.log("데이터를 불러오지 못했습니다.");
    }
}

const addCompany = async (data) => {
    try{
        const response = await axios.post("http://localhost:8080/company/add", data, {
            headers:{
                'Content-Type' : 'application/json',
            }
        })
        return response.data;
    } catch (e) {
        console.log("데이터를 저장하지 못했습니다.");
    }
}

const deleteCompany = async (id) => {
    try{
        const response = await axios.get("http://localhost:8080/company/delete", {
            params : {
                'id': id,
            }
        });
        return response.data;
    } catch (e) {
        console.log("데이터를 삭제하는데 실패했습니다.");
    }
}

const updateCompanyList = async (submitData, editNum) => {
    try{
        const response = await axios.post("http://localhost:8080/company/update",submitData,{
            params : {
                'id' : editNum,
            }
        });
        return response.data;
    } catch (e) {
        console.log("데이터를 업데이트하는데 오류가 발생했습니다.");
    }
}

export {getCompanyList, addCompany, deleteCompany, updateCompanyList};
