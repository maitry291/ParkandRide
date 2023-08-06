package com.example.parkandride.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkandride.R;
import com.example.parkandride.models.Message;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0017\u0018B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/parkandride/adapters/MessageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "messageModalArrayList", "Ljava/util/ArrayList;", "Lcom/example/parkandride/models/Message;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getContext", "()Landroid/content/Context;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BotViewHolder", "UserViewHolder", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: MessageAdapter.kt */
public final class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<Message> messageModalArrayList;

    public MessageAdapter(Context context2, ArrayList<Message> messageModalArrayList2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(messageModalArrayList2, "messageModalArrayList");
        this.context = context2;
        this.messageModalArrayList = messageModalArrayList2;
    }

    public final Context getContext() {
        return this.context;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/example/parkandride/adapters/MessageAdapter$UserViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "userMsg", "Landroid/widget/TextView;", "getUserMsg", "()Landroid/widget/TextView;", "setUserMsg", "(Landroid/widget/TextView;)V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: MessageAdapter.kt */
    public static final class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userMsg;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UserViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.idTVUser);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.idTVUser");
            this.userMsg = textView;
        }

        public final TextView getUserMsg() {
            return this.userMsg;
        }

        public final void setUserMsg(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.userMsg = textView;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/example/parkandride/adapters/MessageAdapter$BotViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "botMsg", "Landroid/widget/TextView;", "getBotMsg", "()Landroid/widget/TextView;", "setBotMsg", "(Landroid/widget/TextView;)V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: MessageAdapter.kt */
    public static final class BotViewHolder extends RecyclerView.ViewHolder {
        private TextView botMsg;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BotViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.idTVBot);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.idTVBot");
            this.botMsg = textView;
        }

        public final TextView getBotMsg() {
            return this.botMsg;
        }

        public final void setBotMsg(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.botMsg = textView;
        }
    }

    public int getItemViewType(int position) {
        String sender = this.messageModalArrayList.get(position).getSender();
        if (Intrinsics.areEqual((Object) sender, (Object) "user")) {
            return 0;
        }
        if (Intrinsics.areEqual((Object) sender, (Object) "bot")) {
            return 1;
        }
        return -1;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 0) {
            View view = LayoutInflater.from(this.context).inflate(R.layout.user_msg_sample, parent, false);
            Intrinsics.checkNotNullExpressionValue(view, "from(context).inflate(co…_msg_sample,parent,false)");
            return new UserViewHolder(view);
        }
        View view2 = LayoutInflater.from(this.context).inflate(R.layout.bot_msg_sample, parent, false);
        Intrinsics.checkNotNullExpressionValue(view2, "from(context).inflate(co…_msg_sample,parent,false)");
        return new BotViewHolder(view2);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Message message = this.messageModalArrayList.get(position);
        Intrinsics.checkNotNullExpressionValue(message, "messageModalArrayList[position]");
        Message model = message;
        if (Intrinsics.areEqual((Object) model.getSender(), (Object) "user")) {
            ((TextView) holder.itemView.findViewById(R.id.idTVUser)).setText(model.getMsg());
        }
        if (Intrinsics.areEqual((Object) model.getSender(), (Object) "bot")) {
            ((TextView) holder.itemView.findViewById(R.id.idTVBot)).setText(model.getMsg());
        }
    }

    public int getItemCount() {
        return this.messageModalArrayList.size();
    }
}
