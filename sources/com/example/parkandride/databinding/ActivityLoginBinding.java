package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.R;

public final class ActivityLoginBinding implements ViewBinding {
    public final TextView appName;
    public final EditText emailId;
    public final LinearLayout emailView;
    public final ImageView imageView;
    public final AppCompatButton logIn1;
    public final ImageView logo;
    public final LinearLayout password;
    public final EditText passwordLogin;
    public final LottieAnimationView progressLogin;
    public final RelativeLayout relative;
    private final RelativeLayout rootView;
    public final TextView signUp;
    public final TextView tvEmail;
    public final TextView tvPassword;

    private ActivityLoginBinding(RelativeLayout rootView2, TextView appName2, EditText emailId2, LinearLayout emailView2, ImageView imageView2, AppCompatButton logIn12, ImageView logo2, LinearLayout password2, EditText passwordLogin2, LottieAnimationView progressLogin2, RelativeLayout relative2, TextView signUp2, TextView tvEmail2, TextView tvPassword2) {
        this.rootView = rootView2;
        this.appName = appName2;
        this.emailId = emailId2;
        this.emailView = emailView2;
        this.imageView = imageView2;
        this.logIn1 = logIn12;
        this.logo = logo2;
        this.password = password2;
        this.passwordLogin = passwordLogin2;
        this.progressLogin = progressLogin2;
        this.relative = relative2;
        this.signUp = signUp2;
        this.tvEmail = tvEmail2;
        this.tvPassword = tvPassword2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityLoginBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.appName;
        TextView appName2 = (TextView) ViewBindings.findChildViewById(view, R.id.appName);
        if (appName2 != null) {
            id = R.id.email_id;
            EditText emailId2 = (EditText) ViewBindings.findChildViewById(view, R.id.email_id);
            if (emailId2 != null) {
                id = R.id.email_view;
                LinearLayout emailView2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.email_view);
                if (emailView2 != null) {
                    id = R.id.imageView;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView);
                    if (imageView2 != null) {
                        id = R.id.logIn1;
                        AppCompatButton logIn12 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.logIn1);
                        if (logIn12 != null) {
                            id = R.id.logo;
                            ImageView logo2 = (ImageView) ViewBindings.findChildViewById(view, R.id.logo);
                            if (logo2 != null) {
                                id = R.id.password;
                                LinearLayout password2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.password);
                                if (password2 != null) {
                                    id = R.id.password_login;
                                    EditText passwordLogin2 = (EditText) ViewBindings.findChildViewById(view, R.id.password_login);
                                    if (passwordLogin2 != null) {
                                        id = R.id.progress_login;
                                        LottieAnimationView progressLogin2 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.progress_login);
                                        if (progressLogin2 != null) {
                                            id = R.id.relative;
                                            RelativeLayout relative2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relative);
                                            if (relative2 != null) {
                                                id = R.id.signUp;
                                                TextView signUp2 = (TextView) ViewBindings.findChildViewById(view, R.id.signUp);
                                                if (signUp2 != null) {
                                                    id = R.id.tv_email;
                                                    TextView tvEmail2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_email);
                                                    if (tvEmail2 != null) {
                                                        id = R.id.tv_password;
                                                        TextView tvPassword2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_password);
                                                        if (tvPassword2 != null) {
                                                            return new ActivityLoginBinding((RelativeLayout) view, appName2, emailId2, emailView2, imageView2, logIn12, logo2, password2, passwordLogin2, progressLogin2, relative2, signUp2, tvEmail2, tvPassword2);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
