package simple.grpcexample;

import io.grpc.stub.StreamObserver;

/**
 * @author hyperglory
 * @date 2017/6/30 11:29
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealnameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息： " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("glory").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息： " + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("glory").setAge(24).setCity("Chongqing").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("hyperglory").setAge(24).setCity("Beijing").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("lsj").setAge(24).setCity("Chongqing").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext: " + value);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse response1 = StudentResponse.newBuilder().setName("glory").setAge(24).setCity("Chongiqng").build();
                StudentResponse response2 = StudentResponse.newBuilder().setName("hyperglory").setAge(24).setCity("Beijing").build();
                StudentResponse response3 = StudentResponse.newBuilder().setName("lsj").setAge(24).setCity("Chongiqng").build();

                StudentResponseList responseList = StudentResponseList.newBuilder()
                        .addStudentResponse(response1).addStudentResponse(response2).addStudentResponse(response3).build();

                responseObserver.onNext(responseList);
                responseObserver.onCompleted();
            }
        };
    }
}

