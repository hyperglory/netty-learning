package simple.grpcexample;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author hyperglory
 * @date 2017/6/30 16:19
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        MyResponse myResponse = blockingStub.getRealnameByUsername(MyRequest.newBuilder().setUsername("glory").build());

        System.out.println(myResponse.getRealname());
    }
}
