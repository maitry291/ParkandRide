package com.example.parkandride.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkandride.CustomerSupport;
import com.example.parkandride.R;
import com.example.parkandride.models.Chat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/parkandride/adapters/ChatAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/parkandride/adapters/ChatAdapter$ViewHolder;", "context", "Landroid/content/Context;", "list", "Ljava/util/ArrayList;", "Lcom/example/parkandride/models/Chat;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getContext", "()Landroid/content/Context;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: ChatAdapter.kt */
public final class ChatAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final ArrayList<Chat> list;

    public final Context getContext() {
        return this.context;
    }

    public ChatAdapter(Context context2, ArrayList<Chat> list2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(list2, "list");
        this.context = context2;
        this.list = list2;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/example/parkandride/adapters/ChatAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ChatAdapter.kt */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(this.context).inflate(R.layout.sample_chatbox, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Chat chat = this.list.get(position);
        Intrinsics.checkNotNullExpressionValue(chat, "list[position]");
        Chat model = chat;
        ((TextView) holder.itemView.findViewById(R.id.user_name)).setText(model.getName());
        ((TextView) holder.itemView.findViewById(R.id.user_lastmsg)).setText(model.getMsg());
        holder.itemView.setOnClickListener(new ChatAdapter$$ExternalSyntheticLambda0(this, model));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-0  reason: not valid java name */
    public static final void m4onBindViewHolder$lambda0(ChatAdapter this$0, Chat $model, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($model, "$model");
        Intent i = new Intent(this$0.context, CustomerSupport.class);
        i.putExtra("id", $model.getSender());
        i.putExtra("name", $model.getName());
        this$0.context.startActivity(i);
    }
}
