package broadcastreceiver.mine.com.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NetworkChangeReceiver receiver;
    private Button btnAction;
    public static final String ACTION_NHẬN_DIỆN = "tên để nhận diện";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAction = (Button) findViewById(R.id.btn_action);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ACTION_NHẬN_DIỆN,"dữ liệu muốn put");
                intent.setAction(ACTION_NHẬN_DIỆN);
                sendBroadcast(intent);
            }
        });
    }
    //Cách 2 là đăng ký trong java code
    //1 là đăng ký ở onstart
    //2 là đăng ký ở onresume
    //Đối với đăng ký trong đây thì hủy được
    //Khi tắt ứng dụng thì không còn nhận hành động nữa kể cả có gọi unregister hay không

    @Override
    protected void onStart() {
        super.onStart();
        initBroadcastReceiver();
    }
    //Nên đưa unregister vào on pause hơn là on stop là tại vì đôi khi khi xãy ra
    // hiện tượng tràn ram thì ứng dụng sẽ bị tắt mà chưa gọi cái unregister
    //Nhưng mà tùy vào mục đích sử dụng đôi khi chúng ta chuyển qua activity khác nhưng vẫn muốn màn
    // hình cũ vẫn lắng nghe sự kiện thì phải để on stop
    //Nếu màn hình nhận hành động bị tắt khi mà chưa thực hiện hành động unregister thì sẽ xãy ra lỗi
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void initBroadcastReceiver(){
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
       // filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction(ACTION_NHẬN_DIỆN);
        registerReceiver(receiver, filter);
    }
}
