package simple.grpcexample;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

/**
 * @author hyperglory
 * @date 2017/6/30 16:19
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub asyncStub = StudentServiceGrpc.newStub(managedChannel);

        MyResponse myResponse = blockingStub.getRealnameByUsername(MyRequest.newBuilder().setUsername("glory").build());

        System.out.println(myResponse.getRealname());

        System.out.println("---------------------");

        Iterator<StudentResponse> iter = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(24).build());

        while (iter.hasNext()) {
            StudentResponse response = iter.next();

            System.out.println(response);
        }

        System.out.println("---------------------");

        StreamObserver<StudentResponseList> responseObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(System.out::println);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };

        StreamObserver<StudentRequest> requestObserver = asyncStub.getStudentsWrapperByAges(responseObserver);
        requestObserver.onNext(StudentRequest.newBuilder().setAge(24).build());
        requestObserver.onNext(StudentRequest.newBuilder().setAge(24).build());
        requestObserver.onNext(StudentRequest.newBuilder().setAge(24).build());
        requestObserver.onCompleted();
    }
}
