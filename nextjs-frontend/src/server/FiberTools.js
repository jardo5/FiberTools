import axios from 'axios';

const FIBER_TOOLS_API_BASE_URL = "http://localhost:8080/fiber";

class FiberToolsService {
    
    generateFiberColor(fiberCount) {
        return axios.get(`${FIBER_TOOLS_API_BASE_URL}/${fiberCount}`)
          .then(response => {
            console.log(response);
            return response.data;
          })
          .catch(error => {
            console.log(error);
          });
      }      
}

export default new FiberToolsService();