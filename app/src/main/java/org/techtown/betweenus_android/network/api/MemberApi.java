package org.techtown.betweenus_android.network.api;

import org.techtown.betweenus_android.model.Member;
import org.techtown.betweenus_android.model.Study;
import org.techtown.betweenus_android.model.StudyList;
import org.techtown.betweenus_android.network.Response;
import org.techtown.betweenus_android.network.request.StudyApplyRequest;
import org.techtown.betweenus_android.network.request.StudyRequest;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MemberApi {

    @GET("/member/study/{studyIdx}")
    Single<retrofit2.Response<Response<List<Member>>>> getStudyMember(@Header("x-access-token") String token,
                                                                      @Path("studyIdx") Integer studyIdx);

    @GET("/member")
    Single<retrofit2.Response<Response>> getStudyOkMember(@Header("x-access-token") String token);
}
