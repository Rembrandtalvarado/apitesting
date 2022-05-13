import org.testng.annotations.Test;
import requests.DeleteRequest;
import requests.GetRequest;
import requests.PostRequest;

public class TMDB {
    @Test
    public void getToken(){
        GetRequest getRequest = new GetRequest();
        getRequest.generateToken();
    }

    @Test
    public void validateTokenLogin(){
        PostRequest postRequest = new PostRequest();
        postRequest.validateToken();
    }
    @Test
    public void createSession(){
        PostRequest postRequest = new PostRequest();
        postRequest.createSession();
    }

    @Test
    public void rateMovie(){
        PostRequest postRequest = new PostRequest();
        postRequest.rateMovie(808, 10);
    }

    @Test
    public void deleteRatingMovie(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteRating(808);
    }

    @Test
    public void createList(){
        PostRequest postRequest = new PostRequest();
        postRequest.createList();
    }

    @Test
    public void getListInfo(){
        GetRequest getRequest = new GetRequest();
        getRequest.getListMovies(8202185);
    }
}
