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
}
