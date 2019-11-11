package com.shell.money.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shell.Bean.OnLineBean;
import com.shell.R;
import com.shell.money.Bean.CardUnderBean;
import com.shell.utils.GetTwoLetter;

import java.util.List;


public class MoneyPageAdapter extends BaseAdapter {
    private Context context;
    List<CardUnderBean.ResultDataBean> list;
    private static final int TYPE_HASIMAGE = 0;  //  偶数
    private static final int TYPE_NOIMG = 1;    //   奇数


    public MoneyPageAdapter(List<CardUnderBean.ResultDataBean> resultList, FragmentActivity activity) {
        this.context = activity;
        this.list = resultList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_HASIMAGE;
        } else {
            return TYPE_NOIMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        VideoHolder1 videoHolder1 = null;
        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE_HASIMAGE: //偶数
                    LayoutInflater inflater = LayoutInflater.from(context);
                    convertView = inflater.inflate(R.layout.item_card_under, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                    break;

                case TYPE_NOIMG:    //奇数
                    LayoutInflater inflater1 = LayoutInflater.from(context);
                    convertView = inflater1.inflate(R.layout.item_card_under_ji, null);
                    videoHolder1 = new VideoHolder1(convertView);
                    convertView.setTag(videoHolder1);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_HASIMAGE:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_NOIMG:
                    videoHolder1 = (VideoHolder1) convertView.getTag();
                    break;

            }
        }


        CardUnderBean.ResultDataBean dataBean = list.get(position);
        String typeCode = dataBean.getBusiCode();
        String status = dataBean.getStatus();
        switch (type) {
            case TYPE_HASIMAGE:
                //质押
                if (typeCode.equals("pledge")) {
                    viewHolder.one.setText("-" + GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.one.setTextColor(Color.parseColor("#F4376D"));
                    viewHolder.two.setText(R.string.or_zhiya);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }

                    //充值
                } else if (typeCode.equals("charge")) {
                    viewHolder.one.setText("+" + GetTwoLetter.getFour(dataBean.getOperateAmount())+ "");
                    viewHolder.two.setText(R.string.rmb_cz);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }

                    //提币
                } else if (typeCode.equals("withdraw")) {
                    viewHolder.one.setText("-" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.two.setText(R.string.rmb_tibi);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#F4376D"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }

                    //清算
                } else if (typeCode.equals("settle")) {
                    viewHolder.one.setText("-" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.two.setText(R.string.rmb_qingsuan);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#F4376D"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }
                    //订单
                } else if (typeCode.equals("order")) {
                    viewHolder.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.two.setText(R.string.order);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }
                } else if (typeCode.equals("reward")) {
                    viewHolder.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.two.setText(R.string.reward);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }
                    //矿池
                }else if (typeCode.equals("minePool")) {
                    viewHolder.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    viewHolder.two.setText(R.string.index_kc);
                    viewHolder.four.setText(dataBean.getCreateTime());
                    viewHolder.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        viewHolder.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        viewHolder.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        viewHolder.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        viewHolder.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        viewHolder.three.setText(R.string.failure);
                    }
                }

                break;
            case TYPE_NOIMG:
                if (typeCode.equals("pledge")) {
                    videoHolder1.one.setText("-" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.one.setTextColor(Color.parseColor("#F4376D"));
                    videoHolder1.two.setText(R.string.or_zhiya);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }

                    //充值
                } else if (typeCode.equals("charge")) {
                    videoHolder1.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.rmb_cz);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }

                    //提币
                } else if (typeCode.equals("withdraw")) {
                    videoHolder1.one.setText("-" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.rmb_tibi);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#F4376D"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }

                    //清算
                } else if (typeCode.equals("settle")) {
                    videoHolder1.one.setText("-" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.rmb_qingsuan);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#F4376D"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }
                }else if (typeCode.equals("order")) {
                    videoHolder1.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.order);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }
                } else if (typeCode.equals("reward")) {
                    videoHolder1.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.reward);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }
                    //矿池
                }else if (typeCode.equals("minePool")) {
                    videoHolder1.one.setText("+" +  GetTwoLetter.getFour(dataBean.getOperateAmount()) + "");
                    videoHolder1.two.setText(R.string.index_kc);
                    videoHolder1.four.setText(dataBean.getCreateTime());
                    videoHolder1.one.setTextColor(Color.parseColor("#80C35F"));
                    if (status.equals("10")) {
                        videoHolder1.three.setText(R.string.processed);
                    } else if (status.equals("20")) {
                        videoHolder1.three.setText(R.string.processing);
                    } else if (status.equals("21")) {
                        videoHolder1.three.setText(R.string.block_confirmation);
                    } else if (status.equals("30")) {
                        videoHolder1.three.setText(R.string.successful);
                    } else if (status.equals("40")) {
                        videoHolder1.three.setText(R.string.failure);
                    }
                }
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        View view;
        private final TextView one;
        private final TextView two;
        private final TextView three;
        private final TextView four;
        private final LinearLayout GenView;


        ViewHolder(View view) {
            this.view = view;
            one = (TextView) view.findViewById(R.id.tv_one);
            two = (TextView) view.findViewById(R.id.tv_two);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            GenView = view.findViewById(R.id.rootView);

        }
    }


    static class VideoHolder1 {
        View view;
        private final TextView one;
        private final TextView two;
        private final TextView three;
        private final TextView four;
        private final LinearLayout GenView;


        VideoHolder1(View view) {
            this.view = view;
            one = (TextView) view.findViewById(R.id.tv_one);
            two = (TextView) view.findViewById(R.id.tv_two);
            three = (TextView) view.findViewById(R.id.tv_three);
            four = (TextView) view.findViewById(R.id.tv_four);
            GenView = view.findViewById(R.id.rootView);
        }
    }
}
