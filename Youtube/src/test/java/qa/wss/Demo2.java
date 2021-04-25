package qa.wss;

import java.time.Duration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;

public class Demo2 {
	private static ActorSystem actorSystem;

	public static void main(String[] args) {
		try {
			System.out.println("hello1");
//			System.out.println("hello");

			actorSystem = ActorSystem.create("web_socket_test_automation_system");
			new TestKit(actorSystem) {
				{
					final ActorRef clientReference = actorSystem.actorOf(Props.create(WebSocketClientActor.class));
					final String msg = "Hello Server";
					// Stimulation: Send echo to server's web socket
					clientReference.tell(msg, getRef());
					// Tests {
					// The expected message should be equal to msg
					expectMsg(Duration.ofSeconds(2), msg);
					// No more messages are expected.
					expectNoMessage();
					// }
				}
			};

			actorSystem.terminate();
			actorSystem = null;
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
